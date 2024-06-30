package Capa_escucha;

import Capa_negocio.TipoActivoService;
import entidad.TipoActivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TipoActivoForm extends JFrame {
    private JLabel lblCodigo, lblNombre;
    private JTextField txtCodigo, txtNombre;
    private JButton btnGuardar, btnEliminar, btnBuscar;
    private JTable tablaTipoActivos;
    private DefaultTableModel modeloTabla;
    private TipoActivoService tipoActivoService;

    public TipoActivoForm() {
        super("Gestión de Tipos de Activo");

        // Configuración de la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Inicialización del servicio
        tipoActivoService = new TipoActivoService();

        // Componentes
        lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField(10);
        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");

        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        tablaTipoActivos = new JTable(modeloTabla);
        actualizarTabla();

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Agregar componentes al panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblCodigo, constraints);

        constraints.gridx = 1;
        panel.add(txtCodigo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblNombre, constraints);

        constraints.gridx = 1;
        panel.add(txtNombre, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(btnEliminar, constraints);

        constraints.gridx = 1;
        panel.add(btnBuscar, constraints);

        // Agregar panel y tabla al frame
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(tablaTipoActivos), BorderLayout.CENTER);

        // Manejo de eventos
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoActivo();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTipoActivo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTipoActivo();
            }
        });

        setVisible(true);
    }

    private void guardarTipoActivo() {
        try {
       //     int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();

            TipoActivo tipoActivo = new TipoActivo();
         //   tipoActivo.setCodigo(codigo);
            tipoActivo.setNombre(nombre);

            tipoActivoService.guardarTipoActivo(tipoActivo);

            JOptionPane.showMessageDialog(this, "Tipo de Activo guardado exitosamente");
            actualizarTabla();
        } catch (NumberFormatException e) {// hacer que oblique a poner algo en los campos
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido");
        }
    }

    private void eliminarTipoActivo() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());

            tipoActivoService.eliminarTipoActivo(codigo);

            JOptionPane.showMessageDialog(this, "Tipo de Activo eliminado exitosamente");
            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido");
        }
    }

    private void buscarTipoActivo() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());

            TipoActivo tipoActivo = tipoActivoService.buscarTipoActivo(codigo);

            if (tipoActivo != null) {
                txtNombre.setText(tipoActivo.getNombre());
                JOptionPane.showMessageDialog(this, "Tipo de Activo encontrado");
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de Activo no encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido");
        }
    }

    private void actualizarTabla() {
        List<TipoActivo> tiposActivos = tipoActivoService.obtenerTodosLosTiposDeActivos();
        modeloTabla.setRowCount(0);

        for (TipoActivo tipoActivo : tiposActivos) {
            modeloTabla.addRow(new Object[]{tipoActivo.getCodigo(), tipoActivo.getNombre()});
        }
    }

    public static void main(String[] args) {
        // Ejemplo para probar la interfaz TipoActivoForm
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipoActivoForm();
            }
        });
    }
}
