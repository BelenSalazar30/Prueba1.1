/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_escucha;
import entidad.DepreciacionDetalle;
import Capa_negocio.DepreciacionDetalleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author belen
 */
public class DepreciacionDetalleForm extends JFrame {
    private JLabel lblId, lblIdDepreciacionCabecera, lblIdActivo, lblValorDepreciado;
    private JTextField txtId, txtIdDepreciacionCabecera, txtIdActivo, txtValorDepreciado;
    private JButton btnGuardar, btnBuscar, btnActualizar, btnEliminar, btnMenu;
    private JTable tableDetalles;
    private DefaultTableModel tableModel;
    private DepreciacionDetalleService detalleService;

    public DepreciacionDetalleForm() {
        super("Gestión de Detalles de Depreciación");

        // Configuración de la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        // Inicialización del servicio
        detalleService = new DepreciacionDetalleService();

        // Componentes
        lblId = new JLabel("ID:");
        txtId = new JTextField(10);
        lblIdDepreciacionCabecera = new JLabel("ID Depreciación Cabecera:");
        txtIdDepreciacionCabecera = new JTextField(10);
        lblIdActivo = new JLabel("ID Activo:");
        txtIdActivo = new JTextField(10);
        lblValorDepreciado = new JLabel("Valor Depreciado:");
        txtValorDepreciado = new JTextField(10);
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnMenu = new JButton("Menú Principal");

        // Configurar la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "ID Depreciación Cabecera", "ID Activo", "Valor Depreciado"}, 0);
        tableDetalles = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableDetalles);

        // Panel y layout
        JPanel panel = new JPanel(new GridBagLayout());
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
        panel.add(lblIdDepreciacionCabecera, constraints);

        constraints.gridx = 1;
        panel.add(txtIdDepreciacionCabecera, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblIdActivo, constraints);

        constraints.gridx = 1;
        panel.add(txtIdActivo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lblValorDepreciado, constraints);

        constraints.gridx = 1;
        panel.add(txtValorDepreciado, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(btnBuscar, constraints);

        constraints.gridx = 1;
        panel.add(btnActualizar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(btnEliminar, constraints);

        constraints.gridx = 1;
        panel.add(btnMenu, constraints);

        // Configuración del panel para la tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Detalles de Depreciación"));

        // Configuración del frame principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(tablePanel, BorderLayout.CENTER);

        // Manejo de eventos
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDepreciacionDetalle();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDepreciacionDetalle();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDepreciacionDetalle();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDepreciacionDetalle();
            }
        });

        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirigirMenuPrincipal();
            }
        });

        setVisible(true);
    }

    private void guardarDepreciacionDetalle() {
        // Obtener datos de los campos de texto
        int idDepreciacionCabecera = Integer.parseInt(txtIdDepreciacionCabecera.getText());
        int idActivo = Integer.parseInt(txtIdActivo.getText());
        double valorDepreciado = Double.parseDouble(txtValorDepreciado.getText());

        // Crear objeto DepreciacionDetalle con los datos ingresados
        DepreciacionDetalle detalle = new DepreciacionDetalle();
        detalle.setIdDepreciacionCabecera(idDepreciacionCabecera);
        detalle.setIdActivo(idActivo);
        detalle.setValorDepreciado(valorDepreciado);

        // Llamar al servicio para guardar el detalle de depreciación
        detalleService.guardarDepreciacionDetalle(detalle);

        // Actualizar la tabla
        actualizarTabla();
    }

    private void buscarDepreciacionDetalle() {
        // Obtener ID del detalle de depreciación a buscar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para buscar el detalle de depreciación
        DepreciacionDetalle detalle = detalleService.buscarDepreciacionDetalle(id);

        // Mostrar los datos del detalle de depreciación encontrado en los campos de texto
        if (detalle != null) {
            txtIdDepreciacionCabecera.setText(String.valueOf(detalle.getIdDepreciacionCabecera()));
            txtIdActivo.setText(String.valueOf(detalle.getIdActivo()));
            txtValorDepreciado.setText(String.valueOf(detalle.getValorDepreciado()));
        } else {
            JOptionPane.showMessageDialog(this, "Detalle de Depreciación no encontrado");
        }
    }

    private void actualizarDepreciacionDetalle() {
        // Obtener datos de los campos de texto
        int id = Integer.parseInt(txtId.getText());
        int idDepreciacionCabecera = Integer.parseInt(txtIdDepreciacionCabecera.getText());
        int idActivo = Integer.parseInt(txtIdActivo.getText());
        double valorDepreciado = Double.parseDouble(txtValorDepreciado.getText());

        // Crear objeto DepreciacionDetalle con los datos ingresados
        DepreciacionDetalle detalle = new DepreciacionDetalle();
        detalle.setId(id);
        detalle.setIdDepreciacionCabecera(idDepreciacionCabecera);
        detalle.setIdActivo(idActivo);
        detalle.setValorDepreciado(valorDepreciado);

        // Llamar al servicio para actualizar el detalle de depreciación
        detalleService.actualizarDepreciacionDetalle(detalle);

        // Actualizar la tabla
        actualizarTabla();
    }

    private void eliminarDepreciacionDetalle() {
        // Obtener ID del detalle de depreciación a eliminar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para eliminar el detalle de depreciación
        detalleService.eliminarDepreciacionDetalle(id);

        // Actualizar la tabla
        actualizarTabla();
    }

    private void redirigirMenuPrincipal() {
        // Redirigir al menú principal
        this.dispose(); // Cerrar la ventana actual
    //    new MenuPrincipal(); // Abrir el menú principal (asegúrate de tener esta clase creada)
    }

    private void actualizarTabla() {
        // Limpiar la tabla antes de actualizar
        tableModel.setRowCount(0);

        // Obtener todos los detalles de depreciación y agregarlos a la tabla
        List<DepreciacionDetalle> detalles = detalleService.obtenerTodosLosDetalles();
        if (detalles != null) {
            for (DepreciacionDetalle detalle : detalles) {
                Object[] row = {detalle.getId(), detalle.getIdDepreciacionCabecera(), detalle.getIdActivo(), detalle.getValorDepreciado()};
                tableModel.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        // Ejemplo para probar el formulario DepreciacionDetalleForm
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DepreciacionDetalleForm();
            }
        });
    }
}
