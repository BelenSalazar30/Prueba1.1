/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_escucha;

import entidad.DepreciacionCabecera;
import Capa_negocio.DepreciacionCabeceraService;

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
public class DepreciacionCabeceraForm extends JFrame {
    private JLabel lblId, lblFecha, lblTotalDepreciacion;
    private JTextField txtId, txtFecha, txtTotalDepreciacion;
    private JButton btnGuardar, btnBuscar, btnActualizar, btnEliminar, btnMenu;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private DepreciacionCabeceraService depreciacionCabeceraService;

    public DepreciacionCabeceraForm() {
        super("Gestión de Depreciación Cabecera");

        // Configuración de la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Inicialización del servicio
        depreciacionCabeceraService = new DepreciacionCabeceraService();

        // Componentes
        lblId = new JLabel("ID:");
        txtId = new JTextField(10);
        lblFecha = new JLabel("Fecha:");
        txtFecha = new JTextField(20);
        lblTotalDepreciacion = new JLabel("Total Depreciación:");
        txtTotalDepreciacion = new JTextField(10);
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnMenu = new JButton("Menú Principal");

        // Configurar la tabla
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Fecha", "Total Depreciación"});
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
        panel.add(lblFecha, constraints);

        constraints.gridx = 1;
        panel.add(txtFecha, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblTotalDepreciacion, constraints);

        constraints.gridx = 1;
        panel.add(txtTotalDepreciacion, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(btnBuscar, constraints);

        constraints.gridx = 1;
        panel.add(btnActualizar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(btnEliminar, constraints);

        constraints.gridx = 1;
        panel.add(btnMenu, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
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
                guardarDepreciacionCabecera();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDepreciacionCabecera();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDepreciacionCabecera();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDepreciacionCabecera();
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

        // Obtener todos los registros y agregarlos a la tabla
        List<DepreciacionCabecera> depreciaciones = depreciacionCabeceraService.obtenerTodasDepreciaciones();
        for (DepreciacionCabecera depreciacion : depreciaciones) {
            tableModel.addRow(new Object[]{
                    depreciacion.getId(),
                    depreciacion.getFecha(),
                    depreciacion.getTotalDepreciacion()
            });
        }
    }

    private void guardarDepreciacionCabecera() {
        // Obtener datos de los campos de texto
        String fecha = txtFecha.getText();
        double totalDepreciacion = Double.parseDouble(txtTotalDepreciacion.getText());

        // Crear objeto DepreciacionCabecera con los datos ingresados
        DepreciacionCabecera depreciacion = new DepreciacionCabecera();
        depreciacion.setFecha(fecha);
        depreciacion.setTotalDepreciacion(totalDepreciacion);

        // Llamar al servicio para guardar el registro
        depreciacionCabeceraService.guardarDepreciacionCabecera(depreciacion);

        // Limpiar campos después de guardar
        limpiarCampos();

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void buscarDepreciacionCabecera() {
        // Obtener ID del registro a buscar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para buscar el registro
        DepreciacionCabecera depreciacion = depreciacionCabeceraService.buscarDepreciacionCabecera(id);

        // Mostrar los datos del registro encontrado en los campos de texto
        if (depreciacion != null) {
            txtFecha.setText(depreciacion.getFecha());
            txtTotalDepreciacion.setText(String.valueOf(depreciacion.getTotalDepreciacion()));
        } else {
            JOptionPane.showMessageDialog(this, "Registro no encontrado");
        }
    }

    private void actualizarDepreciacionCabecera() {
        // Obtener datos de los campos de texto
        int id = Integer.parseInt(txtId.getText());
        String fecha = txtFecha.getText();
        double totalDepreciacion = Double.parseDouble(txtTotalDepreciacion.getText());

        // Crear objeto DepreciacionCabecera con los datos ingresados
        DepreciacionCabecera depreciacion = new DepreciacionCabecera();
        depreciacion.setId(id);
        depreciacion.setFecha(fecha);
        depreciacion.setTotalDepreciacion(totalDepreciacion);

        // Llamar al servicio para actualizar el registro
        depreciacionCabeceraService.actualizarDepreciacionCabecera(depreciacion);

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void eliminarDepreciacionCabecera() {
        // Obtener ID del registro a eliminar
        int id = Integer.parseInt(txtId.getText());

        // Llamar al servicio para eliminar el registro
        depreciacionCabeceraService.eliminarDepreciacionCabecera(id);

        // Limpiar campos después de eliminar
        limpiarCampos();

        // Recargar datos en la tabla
        cargarDatosTabla();
    }

    private void limpiarCampos() {
        // Limpiar campos de texto
        txtId.setText("");
        txtFecha.setText("");
        txtTotalDepreciacion.setText("");
    }

    private void redirigirMenuPrincipal() {
        // Redirigir al menú principal
        this.dispose(); // Cerrar la ventana actual
//        new MenuPrincipal(); // Abrir el menú principal (asegúrate de tener esta clase creada)
    }

    public static void main(String[] args) {
        // Ejemplo para probar la interfaz DepreciacionCabeceraForm
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DepreciacionCabeceraForm();
            }
        });
    }
}

