import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LanzamientoDeDados extends JFrame {

    private DadoPanel dado1, dado2; // Paneles para cada dado
    private JButton lanzarButton;

    public LanzamientoDeDados() {
        setTitle("Lanzamiento de Dados");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de los dados
        JPanel panelDados = new JPanel();
        panelDados.setLayout(new GridLayout(1, 2));
        
        dado1 = new DadoPanel();
        dado2 = new DadoPanel();

        panelDados.add(dado1);
        panelDados.add(dado2);

        // Botón para lanzar los dados
        lanzarButton = new JButton("Lanzar");
        lanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzarDados();
            }
        });

        add(panelDados, BorderLayout.CENTER);
        add(lanzarButton, BorderLayout.SOUTH);
    }

    // Método para lanzar los dados con hilos
    private void lanzarDados() {
        Thread hiloDado1 = new Thread(() -> dado1.lanzar());
        Thread hiloDado2 = new Thread(() -> dado2.lanzar());

        hiloDado1.start();
        hiloDado2.start();
    }

    // Clase interna para representar el panel de un dado
    class DadoPanel extends JPanel {
        private int valor; // Valor actual del dado

        public DadoPanel() {
            this.valor = 1; // Valor inicial
            setPreferredSize(new Dimension(100, 100));
        }

        // Método para lanzar el dado y obtener un valor al azar
        public void lanzar() {
            Random random = new Random();
            for (int i = 0; i < 10; i++) { // Simulación del "giro" de los dados
                valor = random.nextInt(6) + 1;
                repaint(); // Redibujo con el nuevo valor de los dados
                try {
                    Thread.sleep(100); // Pausa para simular la animación
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Pintar el valor del dado
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);

            // Dibujar puntos según el valor del dado, con la disposición real
            switch (valor) {
                case 1 -> dibujarPunto(g, 5);
                case 2 -> {
                    dibujarPunto(g, 1);
                    dibujarPunto(g, 9);
                }
                case 3 -> {
                    dibujarPunto(g, 1);
                    dibujarPunto(g, 5);
                    dibujarPunto(g, 9);
                }
                case 4 -> {
                    dibujarPunto(g, 1);
                    dibujarPunto(g, 3);
                    dibujarPunto(g, 7);
                    dibujarPunto(g, 9);
                }
                case 5 -> {
                    dibujarPunto(g, 1);
                    dibujarPunto(g, 3);
                    dibujarPunto(g, 5);
                    dibujarPunto(g, 7);
                    dibujarPunto(g, 9);
                }
                case 6 -> {
                    dibujarPunto(g, 1);
                    dibujarPunto(g, 3);
                    dibujarPunto(g, 4);
                    dibujarPunto(g, 6);
                    dibujarPunto(g, 7);
                    dibujarPunto(g, 9);
                }
            }
        }

        // Método para dibujar los puntos del dado
        private void dibujarPunto(Graphics g, int posicion) {
            int x = getWidth() / 3;
            int y = getHeight() / 3;

            // Matriz 3x3 para las posiciones de los puntos
            int[][] posiciones = {
                    {x / 2, y / 2},           // Posición 1 - Esquina superior izquierda
                    {x + x / 2, y / 2},       // Posición 2 - Medio superior
                    {2 * x + x / 2, y / 2},   // Posición 3 - Esquina superior derecha
                    {x / 2, y + y / 2},       // Posición 4 - Medio izquierdo
                    {x + x / 2, y + y / 2},   // Posición 5 - Centro
                    {2 * x + x / 2, y + y / 2}, // Posición 6 - Medio derecho
                    {x / 2, 2 * y + y / 2},   // Posición 7 - Esquina inferior izquierda
                    {x + x / 2, 2 * y + y / 2}, // Posición 8 - Medio inferior
                    {2 * x + x / 2, 2 * y + y / 2} // Posición 9 - Esquina inferior derecha
            };

            int[] punto = posiciones[posicion - 1];
            g.fillOval(punto[0] - 5, punto[1] - 5, 10, 10); // Dibuja el círculo en la posición
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LanzamientoDeDados frame = new LanzamientoDeDados();
            frame.setVisible(true);
        });
    }
}

