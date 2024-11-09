import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoTragamonedas extends JFrame {
    private RuedaPanel rueda1, rueda2, rueda3;
    private JButton spinButton;
    private JLabel resultadoLabel;

    public JuegoTragamonedas() {
        setTitle("Juego Tragamonedas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para las ruedas del tragamonedas
        JPanel panelRuedas = new JPanel();
        panelRuedas.setLayout(new GridLayout(1, 3));

        rueda1 = new RuedaPanel();
        rueda2 = new RuedaPanel();
        rueda3 = new RuedaPanel();

        panelRuedas.add(rueda1);
        panelRuedas.add(rueda2);
        panelRuedas.add(rueda3);

        // Botón para girar
        spinButton = new JButton("Girar");
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                girarRuedas();
            }
        });

        // Etiqueta de resultado
        resultadoLabel = new JLabel("¡Buena suerte!", SwingConstants.CENTER);

        add(panelRuedas, BorderLayout.CENTER);
        add(spinButton, BorderLayout.SOUTH);
        add(resultadoLabel, BorderLayout.NORTH);
    }

    // Método para girar las ruedas
    private void girarRuedas() {
        resultadoLabel.setText("Girando...");
        Thread hiloRueda1 = new Thread(() -> rueda1.girar());
        Thread hiloRueda2 = new Thread(() -> rueda2.girar());
        Thread hiloRueda3 = new Thread(() -> rueda3.girar());

        hiloRueda1.start();
        hiloRueda2.start();
        hiloRueda3.start();

        // Hilo para verificar el resultado después de que las ruedas se detengan
        new Thread(() -> {
            try {
                hiloRueda1.join();
                hiloRueda2.join();
                hiloRueda3.join();
                verificarResultado();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Método para verificar el resultado
    private void verificarResultado() {
        int valor1 = rueda1.getValor();
        int valor2 = rueda2.getValor();
        int valor3 = rueda3.getValor();

        if (valor1 == valor2 && valor2 == valor3) {
            resultadoLabel.setText("¡Ganaste!");
        } else {
            resultadoLabel.setText("Intenta de nuevo.");
        }
    }

    // Clase interna para representar cada rueda del tragamonedas
    class RuedaPanel extends JPanel {
        private int valor; // Representa el símbolo actual de la rueda
        private String[] simbolos = {"🍒", "🔔", "💎", "🍋", "🍉"}; // Símbolos de ejemplo

        public RuedaPanel() {
            this.valor = 0; // Inicializar con el primer símbolo
            setPreferredSize(new Dimension(100, 100));
            setFont(new Font("SansSerif", Font.BOLD, 40));
        }

        // Método para girar la rueda
        public void girar() {
            Random random = new Random();
            for (int i = 0; i < 20; i++) { // Número de vueltas para simular el giro
                valor = random.nextInt(simbolos.length);
                repaint(); // Redibujar con el nuevo símbolo
                try {
                    Thread.sleep(100); // Pausa para simular el giro
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Obtener el valor actual de la rueda
        public int getValor() {
            return valor;
        }

        // Pintar el símbolo actual
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString(simbolos[valor], getWidth() / 2 - 15, getHeight() / 2 + 15);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuegoTragamonedas frame = new JuegoTragamonedas();
            frame.setVisible(true);
        });
    }
}
