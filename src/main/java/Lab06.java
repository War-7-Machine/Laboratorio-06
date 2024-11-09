import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab06 extends JFrame {
    public Lab06() {
        setTitle("Menú Principal");
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Botón 1: Programa del lanzamiento de dados
        JButton btnDados = new JButton("Lanzamiento de dados");
        add(btnDados);
        
        // Botón 2: Programa del juego de tragamonedas
        JButton btnTragamonedas = new JButton("Tragamonedas");
        add(btnTragamonedas);
        
        // Acción del botón 1: abrir el simulador de lanzamiento de dados
        btnDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanzamientoDeDados Dados = new LanzamientoDeDados();
                Dados.setVisible(true);
            }
        });
        // Acción del botón 2: abrir el simulador de tragamonedas
        btnTragamonedas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JuegoTragamonedas Tragamonedas = new JuegoTragamonedas();
                Tragamonedas.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Lab06().setVisible(true);
        });
    }
} 