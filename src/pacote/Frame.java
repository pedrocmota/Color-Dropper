package pacote;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.commons.io.FileUtils;

public class Frame extends javax.swing.JFrame {

    public static void main(String args[]) throws Exception {
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        frame = new Frame();
        frame.setVisible(true);
    }

    public static String nome = "Color Dropper";
    public static double versão = 1.2;

    public static Frame frame;
    public static Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    public Robot robot;

    public Frame() throws Exception {
        controleDeInstancia();
        initComponents();
        graficos();
    }

    private void controleDeInstancia() {
        File controleDeInstancia = new File(FileUtils.getTempDirectory() + "\\temp.temp");
        if (!controleDeInstancia.exists()) {
            try {
                controleDeInstancia.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        try {
            if (controleDeInstancia.delete() == false) {
                File controleChamada = new File(FileUtils.getTempDirectory() + "\\chamada.temp");
                try {
                    controleChamada.createNewFile();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                System.exit(0);
            }
            FileChannel channel = new RandomAccessFile(controleDeInstancia, "rw").getChannel();
            FileLock lock = channel.lock();
        } catch (Exception e) {
            File controleChamada = new File(FileUtils.getTempDirectory() + "\\chamada.temp");
            try {
                controleChamada.createNewFile();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            System.exit(0);
        }
        new Thread() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        File controleChamada = new File(FileUtils.getTempDirectory() + "\\chamada.temp");
                        if (controleChamada.exists()) {
                            controleChamada.delete();
                            int sta = frame.getExtendedState() & ~JFrame.ICONIFIED & JFrame.NORMAL;
                            frame.setExtendedState(sta);
                            frame.setAlwaysOnTop(true);
                            frame.toFront();
                            frame.requestFocus();
                            frame.setAlwaysOnTop(false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }.start();
    }

    DefaultTableModel modelo;

    public void graficos() throws Exception {
        robot = new Robot();
        setIconImage(new ImageIcon(getClass().getResource("/pacote/icone.png")).getImage());
        setLayout(null);
        setBackground(new Color(0.0f, 0.0f, 0.0f, 0.01f));
        setSize(tamanhoTela.width, tamanhoTela.height);
        setLocation(0, 0);
        painel.setLocation((tamanhoTela.width - painel.getWidth()) / 2, (tamanhoTela.height - painel.getHeight()) / 2);
        modelo = (DefaultTableModel) tabela.getModel();
        JTableHeader header = tabela.getTableHeader();
        header.setBackground(new Color(221, 221, 221));
        header.setOpaque(false);
        header.setFont(tabela.getFont());
        TableColumn coluna1 = tabela.getColumnModel().getColumn(0);
        TableColumn coluna2 = tabela.getColumnModel().getColumn(1);
        TableColumn coluna3 = tabela.getColumnModel().getColumn(2);
        TableColumn coluna4 = tabela.getColumnModel().getColumn(3);
        coluna1.setCellRenderer(new Coluna1Render());
        coluna2.setCellRenderer(new Coluna2Render());
        coluna3.setCellRenderer(new Coluna2Render());
        coluna4.setCellRenderer(new Coluna2Render());
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int i = tabela.getSelectedRow();
                if (i > -1) {
                    setCor((Color) tabela.getValueAt(i, 0), false);
                }
            }
        });
        tabela.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tabela.getActionMap().put("Enter", new AbstractAction() {
            public void actionPerformed(ActionEvent evt) {
                copiar.doClick();
            }
        });
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        painel = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        fechar = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        labelSetor1 = new javax.swing.JLabel();
        labelSetor2 = new javax.swing.JLabel();
        rolagem = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        cubo = new javax.swing.JLabel();
        labelVermelho = new javax.swing.JLabel();
        labelVerde = new javax.swing.JLabel();
        labelAzul = new javax.swing.JLabel();
        copiar = new javax.swing.JButton();
        limpar = new javax.swing.JButton();
        ativo = new javax.swing.JCheckBox();
        box1 = new javax.swing.JComboBox<>();
        txt1 = new javax.swing.JLabel();
        saida = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(nome + " v" + versão);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        painel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(227, 227, 227)));
        painel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                painelMouseDragged(evt);
            }
        });
        painel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                painelMousePressed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/icone.png"))); // NOI18N
        titulo.setText(nome + " v" + versão);
        titulo.setIconTextGap(10);
        titulo.setPreferredSize(new java.awt.Dimension(334, 32));

        minimizar.setBackground(new java.awt.Color(230, 230, 230));
        minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/minimizar.png"))); // NOI18N
        minimizar.setOpaque(true);
        minimizar.setPreferredSize(new java.awt.Dimension(40, 40));
        minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizarMouseExited(evt);
            }
        });

        fechar.setBackground(new java.awt.Color(230, 230, 230));
        fechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/fechar.png"))); // NOI18N
        fechar.setOpaque(true);
        fechar.setPreferredSize(new java.awt.Dimension(40, 40));
        fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fecharMouseExited(evt);
            }
        });

        separador.setForeground(new java.awt.Color(227, 227, 227));
        separador.setPreferredSize(new java.awt.Dimension(698, 10));

        labelSetor1.setBackground(new java.awt.Color(221, 221, 221));
        labelSetor1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        labelSetor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSetor1.setText("Últimas cores");
        labelSetor1.setOpaque(true);
        labelSetor1.setPreferredSize(new java.awt.Dimension(334, 26));

        labelSetor2.setBackground(new java.awt.Color(221, 221, 221));
        labelSetor2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        labelSetor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSetor2.setText("Cor selecionada");
        labelSetor2.setOpaque(true);
        labelSetor2.setPreferredSize(new java.awt.Dimension(334, 26));

        rolagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 217, 217)));
        rolagem.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        rolagem.setPreferredSize(new java.awt.Dimension(334, 258));

        tabela.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cor", "Vermelho", "Verde", "Azul"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setRowHeight(45);
        tabela.setSelectionBackground(new java.awt.Color(122, 189, 242));
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabela.setShowHorizontalLines(false);
        tabela.setShowVerticalLines(false);
        tabela.getTableHeader().setReorderingAllowed(false);
        rolagem.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(45);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(2).setResizable(false);
            tabela.getColumnModel().getColumn(3).setResizable(false);
        }

        cubo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 217, 217)));
        cubo.setOpaque(true);
        cubo.setPreferredSize(new java.awt.Dimension(140, 150));

        labelVermelho.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        labelVermelho.setText("Vermelho:");
        labelVermelho.setPreferredSize(new java.awt.Dimension(188, 30));

        labelVerde.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        labelVerde.setText("Verde:");
        labelVerde.setPreferredSize(new java.awt.Dimension(188, 30));

        labelAzul.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        labelAzul.setText("Azul:");
        labelAzul.setPreferredSize(new java.awt.Dimension(188, 30));

        copiar.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        copiar.setText("Copiar");
        copiar.setFocusable(false);
        copiar.setPreferredSize(new java.awt.Dimension(140, 34));
        copiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarActionPerformed(evt);
            }
        });

        limpar.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        limpar.setText("Limpar");
        limpar.setFocusable(false);
        limpar.setPreferredSize(new java.awt.Dimension(198, 34));
        limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });

        ativo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        ativo.setSelected(true);
        ativo.setText("ATIVAR SELEÇÃO");
        ativo.setFocusPainted(false);
        ativo.setPreferredSize(new java.awt.Dimension(186, 32));

        box1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        box1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RGB Web", "RGB", "Hexadecimal" }));
        box1.setFocusable(false);
        box1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                box1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txt1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt1.setText("Sistema de cores:");

        saida.setEditable(false);
        saida.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        org.jdesktop.layout.GroupLayout painelLayout = new org.jdesktop.layout.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, painelLayout.createSequentialGroup()
                .addContainerGap()
                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(painelLayout.createSequentialGroup()
                        .add(titulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(ativo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(70, 70, 70)
                        .add(minimizar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, 0)
                        .add(fechar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(painelLayout.createSequentialGroup()
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, rolagem, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, labelSetor1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelSetor2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(painelLayout.createSequentialGroup()
                                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, txt1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, box1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, copiar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cubo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(labelVermelho, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(labelAzul, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(labelVerde, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(limpar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .add(saida))))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, separador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(painelLayout.createSequentialGroup()
                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(fechar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(minimizar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, painelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, titulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, ativo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(separador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelSetor1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelSetor2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(rolagem, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(painelLayout.createSequentialGroup()
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, painelLayout.createSequentialGroup()
                                .add(labelVermelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(30, 30, 30)
                                .add(labelVerde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(labelAzul, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, cubo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txt1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(saida)
                            .add(box1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(painelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(copiar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(limpar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(11, 11, 11))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(painel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(painel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    Color fundoNormal = new Color(230, 230, 230);
    Color fundoSelecionado = new Color(216, 216, 216);

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getButton() == 1) {
            if (ativo.isSelected()) {
                setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
                Color cor = robot.getPixelColor(evt.getX(), evt.getY());
                setBackground(new Color(0.0f, 0.0f, 0.0f, 0.01f));
                setCor(cor, true);
            } else {
                frame.setState(Frame.ICONIFIED);
            }
        }
        if (evt.getButton() == 3) {
            ativo.setSelected(!ativo.isSelected());
        }
    }//GEN-LAST:event_formMouseClicked

    public void setCor(Color cor, boolean salvar) {
        cubo.setBackground(cor);
        labelVermelho.setText("Vermelho: " + cor.getRed());
        labelVerde.setText("Verde: " + cor.getGreen());
        labelAzul.setText("Azul: " + cor.getBlue());
        if (salvar) {
            modelo.addRow(new Object[]{cor, cor.getRed() + "", cor.getGreen() + "", cor.getBlue() + ""});
        }
        saida();
    }

    private void fecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fecharMouseClicked
        if (evt.getButton() == 1) {
            System.exit(0);
        }
    }//GEN-LAST:event_fecharMouseClicked

    private void fecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fecharMouseEntered
        fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/fechar2.png")));
        fechar.setBackground(fundoSelecionado);
    }//GEN-LAST:event_fecharMouseEntered

    private void fecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fecharMouseExited
        fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/fechar.png")));
        fechar.setBackground(fundoNormal);
    }//GEN-LAST:event_fecharMouseExited

    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        if (evt.getButton() == 1) {
            frame.setState(Frame.ICONIFIED);
        }
    }//GEN-LAST:event_minimizarMouseClicked

    private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseEntered
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/minimizar2.png")));
        minimizar.setBackground(fundoSelecionado);
    }//GEN-LAST:event_minimizarMouseEntered

    private void minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseExited
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacote/minimizar.png")));
        minimizar.setBackground(fundoNormal);
    }//GEN-LAST:event_minimizarMouseExited

    int xx = 0;
    int yy = 0;

    private void painelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        painel.setLocation(x - xx, y - yy);
    }//GEN-LAST:event_painelMouseDragged

    private void painelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMousePressed
        tabela.clearSelection();
        painel.grabFocus();
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_painelMousePressed

    private void copiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarActionPerformed
        /*String sel = null;
        if (box1.isSelected()) {
            sel = cubo.getBackground().getRed() + "" + cubo.getBackground().getGreen() + " " + cubo.getBackground().getBlue();
        }
        if (box2.isSelected()) {
            sel = cubo.getBackground().getRed() + ", " + cubo.getBackground().getGreen() + ", " + cubo.getBackground().getBlue();
        }
        if (box3.isSelected()) {
            sel = cubo.getBackground().getRed() + outro.getText() + cubo.getBackground().getGreen() + outro.getText() + cubo.getBackground().getBlue();
        }*/
        //Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //StringSelection selecionado = new StringSelection(sel);
        //clipboard.setContents(selecionado, null);
    }//GEN-LAST:event_copiarActionPerformed

    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
        modelo.setRowCount(0);
        labelVermelho.setText("Vermelho:");
        labelVerde.setText("Verde:");
        labelAzul.setText("Azul:");
        cubo.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_limparActionPerformed

    private void box1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_box1PopupMenuWillBecomeInvisible
        saida();
    }//GEN-LAST:event_box1PopupMenuWillBecomeInvisible

    public void saida() {
        if(box1.getSelectedIndex() == 0) {
            saida.setText("rgb(" + cubo.getBackground().getRed() + ", " + cubo.getBackground().getGreen() + ", " + cubo.getBackground().getBlue() + ")");
        }
        if(box1.getSelectedIndex() == 1) {
            saida.setText(cubo.getBackground().getRed() + ", " + cubo.getBackground().getGreen() + ", " + cubo.getBackground().getBlue());
        }
        if(box1.getSelectedIndex() == 2) {
            String hex = String.format("#%02X%02X%02X", cubo.getBackground().getRed(), cubo.getBackground().getGreen(), cubo.getBackground().getBlue()); 
            saida.setText(hex);
        }
    }
    public class Coluna1Render implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground((Color) value);
            if (isSelected) {
                label.setBorder(BorderFactory.createLineBorder(tabela.getSelectionBackground(), 5));
            } else {
                label.setBorder(BorderFactory.createLineBorder(Color.white, 5));
            }
            return label;
        }
    }

    public class Coluna2Render implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
            JLabel label = new JLabel();
            label.setText(value + "");
            label.setFont(tabela.getFont());
            label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            label.setOpaque(true);
            if (isSelected) {
                label.setBackground(tabela.getSelectionBackground());
                label.setForeground(tabela.getSelectionForeground());
            } else {
                label.setBackground(Color.white);
            }
            return label;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox ativo;
    public javax.swing.JComboBox<String> box1;
    public javax.swing.JButton copiar;
    public javax.swing.JLabel cubo;
    public javax.swing.JLabel fechar;
    public javax.swing.ButtonGroup grupo;
    public javax.swing.JLabel labelAzul;
    public javax.swing.JLabel labelSetor1;
    public javax.swing.JLabel labelSetor2;
    public javax.swing.JLabel labelVerde;
    public javax.swing.JLabel labelVermelho;
    public javax.swing.JButton limpar;
    public javax.swing.JLabel minimizar;
    public javax.swing.JPanel painel;
    public javax.swing.JScrollPane rolagem;
    public javax.swing.JTextField saida;
    public javax.swing.JSeparator separador;
    public javax.swing.JTable tabela;
    public javax.swing.JLabel titulo;
    public javax.swing.JLabel txt1;
    // End of variables declaration//GEN-END:variables
}
