package Capa_escucha;

import entidad.Activo;
import Capa_negocio.ActivoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActivoForm extends JFrame {
    private JLabel lblId, lblNombre, lblPeriodos, lblValor, lblTipo;
    private JTextField txtId, txtNombre, txtPeriodos, txtValor, txtTipo;
    private JButton btnGuardar, btnBuscar, btnActualizar, btnEliminar, btnMenu;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private ActivoService activoService;

    public ActivoForm() {
        super("Gestión de Activos");

        // Configuración de la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Inicialización del servicio
        activoService = new ActivoService();

        // Componentes
        lblId = new JLabel("ID:");
        txtId = new JTextField(10);
        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        lblPeriodos = new JLabel("Periodos de depreciación total:");
        txtPeriodos = new JTextField(5);
        lblValor = new JLabel("Valor de compra:");
        txtValor = new JTextField(10);
        lblTipo = new JLabel("Tipo de activo:");
        txtTipo = new JTextField(15);
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnMenu = new JButton("Menú Principal");

        // Configurar la tabla
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Periodos", "Valor", "Tipo"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel y layout
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Agregar componentes al panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblId, constraints);

        constraints.gridx = 1;
        panel.add(txtId, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblNombre, constraints);

        constraints.gridx = 1;
        panel.add(txtNombre, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblPeriodos, constraints);

        constraints.gridx = 1;
        panel.add(txtPeriodos, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lblValor, constraints);

        constraints.gridx = 1;
        panel.add(txtValor, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lblTipo, constraints);

        constraints.gridx = 1;
        panel.add(txtTipo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(btnBuscar, constraints);

        constraints.gridx = 1;
        panel.add(btnActualizar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(btnEliminar, constraints);

        constraints.gridx = 1;
        panel.add(btnMenu, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, constraints);

        // Agregar panel al frame
        getContentPane().add(panel, BorderLayout.CENTER);

        // Manejo de eventos
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarActivo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActivo();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarActivo();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarActivo();
            }
        });

        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirigirMenuPrincipal();
            }
        });

        // Cargar datos iniciales en la tabla
        cargarDatosTabla();

        setVisible(true);
    }

    private void cargarDatosTabla() {
        // Limpiar tabla
        tableModel.setRowCount(0);

        // Obtener todos los activos y agregarlos a la tabla
        List<Activo> activos = activoService.obtenerTodosActivos();
        for (Activo activo : activos) {
            tableModel.addRow(new Object[]{
                    activo.getId(),
                    activo.getNombre(),
                    activo.getPeriodosDepreciacionTotal(),
                    activo.getValorCompra(),
                    activo.getTipoActivo()
            });
        }
    }

    private void guardarActivo() {
        // Obtener datos de los campos de texto
        String nombre = txtNombre.getText();
        int periodos = Integer.parseInt(txtPeriodos.getText());
        double valor = Double.parseDouble(txtValor.getText());
        String tipo = txtTipo.getText();

        // Crear objeto Activo con los datos ingresados
        Activo activo = new Activo();
        activo.setNombre(nombre);
        activo.setPeriodosDepreciacionTotal(periodos);
        activo.setValorCompra(valor);
        activo.setTipoActivo(tipo);

        // Llamar al servicio para guardar el activo
        activoService.guardarActivo(activo);

        // Limpiar campos después de guardar
        limpiarCampos();

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void buscarActivo() {
        // Obtener ID del activo a buscar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para buscar el activo
        Activo activo = activoService.buscarActivo(id);

        // Mostrar los datos del activo encontrado en los campos de texto
        if (activo != null) {
            txtNombre.setText(activo.getNombre());
            txtPeriodos.setText(String.valueOf(activo.getPeriodosDepreciacionTotal()));
            txtValor.setText(String.valueOf(activo.getValorCompra()));
            txtTipo.setText(activo.getTipoActivo());
        } else {
            JOptionPane.showMessageDialog(this, "Activo no encontrado");
        }
    }

    private void actualizarActivo() {
        // Obtener datos de los campos de texto
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        int periodos = Integer.parseInt(txtPeriodos.getText());
        double valor = Double.parseDouble(txtValor.getText());
        String tipo = txtTipo.getText();

        // Crear objeto Activo con los datos ingresados
        Activo activo = new Activo();
        activo.setId(id);
        activo.setNombre(nombre);
        activo.setPeriodosDepreciacionTotal(periodos);
        activo.setValorCompra(valor);
        activo.setTipoActivo(tipo);

        // Llamar al servicio para actualizar el activo
        activoService.actualizarActivo(activo);

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void eliminarActivo() {
        // Obtener ID del activo a eliminar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para eliminar el activo
        activoService.eliminarActivo(id);

        // Limpiar campos después de eliminar
        limpiarCampos();

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void limpiarCampos() {
        // Limpiar campos de texto
        txtId.setText("");
        txtNombre.setText("");
        txtPeriodos.setText("");
        txtValor.setText("");
        txtTipo.setText("");
    }

    private void redirigirMenuPrincipal() {
        // Redirigir al menú principal
        this.dispose(); // Cerrar la ventana actual
    //    new MenuPrincipal(); // Abrir el menú principal (asegúrate de tener esta clase creada)
    }

    public static void main(String[] args) {
        // Ejemplo para probar la interfaz ActivoForm
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ActivoForm();
            }
        });
    }
}
