package practica4.view;

import practica4.Event;
import practica4.EventListener;
import practica4.Main;
import practica4.Map;
import practica4.controller.StructureTechnique;
import practica4.controller.ControllerEvent;
import practica4.model.Model;
import practica4.model.ModelEvent;

/**
 *
 * @author usuario
 */
public class View extends javax.swing.JFrame implements EventListener {
    private Main main;
    private Model model;
    private int pobDimension;
    private boolean [] criterias;

    /**
     * Creates new form Vista
     */
    public View(Main main) {
        this.main = main;
        this.model = main.getModel();
        this.pobDimension = 8;
        this.criterias = new boolean[3];
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Map");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mapComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelOriginSelected = new javax.swing.JLabel();
        originRadio = new javax.swing.JRadioButton();
        passingRadio = new javax.swing.JRadioButton();
        destRadio = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        labelPassingSelected = new javax.swing.JLabel();
        labelDestSelected = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        distanceBox = new javax.swing.JCheckBox();
        timeBox = new javax.swing.JCheckBox();
        moneyBox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        structureComboBox = new javax.swing.JComboBox<>();
        progressBar = new javax.swing.JProgressBar();
        map = new MapDisplay(this.main.getModel(), Map.getImage(Map.PITIUSES), this.pobDimension);
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        solutionList = new javax.swing.JList<>();
        buttonStart = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 700));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Origin");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Passing Through");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Destination");

        mapComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(this.showFiles()));
        mapComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MAPS");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select map file");

        labelOriginSelected.setForeground(new java.awt.Color(255, 255, 255));
        labelOriginSelected.setText("<none>");

        originRadio.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(originRadio);
        originRadio.setForeground(new java.awt.Color(255, 255, 255));
        originRadio.setText("Origin");

        passingRadio.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(passingRadio);
        passingRadio.setForeground(new java.awt.Color(255, 255, 255));
        passingRadio.setText("Passing Through");

        destRadio.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(destRadio);
        destRadio.setForeground(new java.awt.Color(255, 255, 255));
        destRadio.setText("Destination");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Select on the map");

        labelPassingSelected.setForeground(new java.awt.Color(255, 255, 255));
        labelPassingSelected.setText("<none>");

        labelDestSelected.setForeground(new java.awt.Color(255, 255, 255));
        labelDestSelected.setText("<none>");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Select Criteria");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        distanceBox.setBackground(new java.awt.Color(0, 51, 51));
        distanceBox.setForeground(new java.awt.Color(255, 255, 255));
        distanceBox.setText("Distance");
        distanceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distanceBoxActionPerformed(evt);
            }
        });

        timeBox.setBackground(new java.awt.Color(0, 51, 51));
        timeBox.setForeground(new java.awt.Color(255, 255, 255));
        timeBox.setText("Time");
        timeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeBoxActionPerformed(evt);
            }
        });

        moneyBox.setBackground(new java.awt.Color(0, 51, 51));
        moneyBox.setForeground(new java.awt.Color(255, 255, 255));
        moneyBox.setText("Money");
        moneyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moneyBoxActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Select Technique");

        structureComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(this.showAlgTechniques()));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mapComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel4))
                            .addComponent(originRadio)
                            .addComponent(passingRadio)
                            .addComponent(destRadio)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(labelOriginSelected)
                            .addComponent(labelPassingSelected)
                            .addComponent(labelDestSelected)
                            .addComponent(distanceBox)
                            .addComponent(timeBox)
                            .addComponent(moneyBox)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(structureComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(originRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passingRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destRadio)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelOriginSelected)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPassingSelected)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDestSelected)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(structureComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanceBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moneyBox)
                .addGap(14, 14, 14))
        );

        progressBar.setForeground(new java.awt.Color(0, 102, 102));

        map.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mapLayout = new javax.swing.GroupLayout(map);
        map.setLayout(mapLayout);
        mapLayout.setHorizontalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mapLayout.setVerticalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ROUTE");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jScrollPane2.setViewportView(solutionList);

        buttonStart.setBackground(new java.awt.Color(0, 102, 102));
        buttonStart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonStart.setForeground(new java.awt.Color(255, 255, 255));
        buttonStart.setText("START");
        buttonStart.setBorderPainted(false);
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        buttonReset.setBackground(new java.awt.Color(0, 102, 102));
        buttonReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonReset.setText("RESET");
        buttonReset.setBorderPainted(false);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(19, 19, 19)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addComponent(buttonStart)
                .addGap(8, 8, 8)
                .addComponent(buttonReset))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapMouseClicked
        System.out.println("Coordx : " + evt.getX() + " Coordy : " + evt.getY());
        
        if (this.buttonGroup1.getSelection() == null) {
            System.out.println("Poblation not selected");
            return;
        }
        
        int n = model.getNPoblations();

        int posX = evt.getX();
        int posY = evt.getY();
        
        for (int i = 0; i < n; i++) {
            int centerX = model.getPoblationX(i);
            int centerY = model.getPoblationY(i);
            
            if (poblationClicked(centerX, centerY, posX, posY)) {
                String pobName = model.getPobName(i);
                System.out.println("Poblation clicked : " + pobName);
                int currentIndex = -1;
                
                if (this.buttonGroup1.isSelected(this.originRadio.getModel())) {
                    currentIndex = model.getOrigin();
                    if (currentIndex != i) {
                        this.labelOriginSelected.setText(pobName);
                        this.main.notify(new ModelEvent(true, i));
                    }
                }
                
                if (this.buttonGroup1.isSelected(this.passingRadio.getModel())) {
                    currentIndex = model.getMiddle();
                    if (currentIndex != i) {
                        this.labelPassingSelected.setText(pobName);
                        this.main.notify(new ModelEvent(i));
                    }
                }
                
                if (this.buttonGroup1.isSelected(this.destRadio.getModel())) {
                    currentIndex = model.getDest();
                    if (currentIndex != i) {
                        this.labelDestSelected.setText(pobName);
                        this.main.notify(new ModelEvent(false, i));
                    }
                }
                this.map.repaint();
                break;
            }
        }
    }//GEN-LAST:event_MapMouseClicked

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        this.buttonStart.setEnabled(true);
        this.progressBar.setIndeterminate(false);
        this.buttonGroup1.clearSelection();

        String emptyPoblation = "<none>";
        this.labelOriginSelected.setText(emptyPoblation);
        this.labelPassingSelected.setText(emptyPoblation);
        this.labelDestSelected.setText(emptyPoblation);
        
        this.distanceBox.setSelected(false);
        this.timeBox.setSelected(false);
        this.moneyBox.setSelected(false);
        
        solutionList.setListData(new String[0]);
        
        this.criterias = new boolean[3];

        this.main.notify(new ControllerEvent());
        this.main.notify(new ModelEvent());
        this.map.reset();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        if (this.model.selectionCompleted()) {
            this.buttonStart.setEnabled(false);

            this.progressBar.setIndeterminate(true);
            
            boolean anyTrue = false;
            for (int i = 0; i < this.criterias.length; i++) {
                if (this.criterias[i]) {
                    anyTrue = true;
                }
            }
            if (!anyTrue) this.criterias[0] = true;
            this.main.notify(new ModelEvent(this.criterias));
            this.main.notify(new ControllerEvent(this.structureComboBox.getSelectedIndex()));
        }
    }//GEN-LAST:event_buttonStartActionPerformed

    private void distanceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distanceBoxActionPerformed
        this.criterias[0] = this.distanceBox.isSelected();
    }//GEN-LAST:event_distanceBoxActionPerformed

    private void timeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeBoxActionPerformed
        this.criterias[1] = this.timeBox.isSelected();
    }//GEN-LAST:event_timeBoxActionPerformed

    private void moneyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moneyBoxActionPerformed
        this.criterias[2] = this.moneyBox.isSelected();
    }//GEN-LAST:event_moneyBoxActionPerformed

    private void mapComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapComboBoxActionPerformed
        if (this.buttonStart.isEnabled()) {
            this.buttonGroup1.clearSelection();

            String emptyPoblation = "<none>";
            this.labelOriginSelected.setText(emptyPoblation);
            this.labelPassingSelected.setText(emptyPoblation);
            this.labelDestSelected.setText(emptyPoblation);

            this.distanceBox.setSelected(false);
            this.timeBox.setSelected(false);
            this.moneyBox.setSelected(false);

            solutionList.setListData(new String[0]);

            this.criterias = new boolean[3];
            
            int i = this.mapComboBox.getSelectedIndex();
            //this.main.notify(new ModelEvent());
            this.main.updateMap(Map.getFile(Map.getByIndex(i)));
            //this.model = main.getModel();
            this.map.updateImage(Map.getImage(Map.getByIndex(i)));
            //
        }
    }//GEN-LAST:event_mapComboBoxActionPerformed

     // Return the euclidean distance between the points passed by parameter
    private boolean poblationClicked(int x, int y, int mousex, int mousey) {
        // X
        int difX = x - mousex;
        int powerX = difX * difX;
        // Y
        int difY = y - mousey;
        int powerY = difY * difY;
        
        int sum = powerX + powerY;
        int result = (int) Math.sqrt(sum);
        
        return result <= this.pobDimension;
    }
    
    private String [] showFiles() {
        String [] files = new String[Map.values().length];
        
        int i = 0;
        for (Map m : Map.values()) {
            files[i] = m.toString();
            i++;
        }
        return files;
    }
    
    private String [] showAlgTechniques() {
        String [] techniques = new String[StructureTechnique.values().length];
        
        int i = 0;
        for (StructureTechnique t : StructureTechnique.values()) {
            techniques[i] = t.toString();
            i++;
        }
        return techniques;
    }

    @Override
    public void notify(Event e) {
        ViewEvent event = (ViewEvent) e;
        this.progressBar.setIndeterminate(false);
        
        this.map.pobSolution(event.indexs);

        int index;
        String [] solution = new String[event.indexs.size()];
        solution[0] = model.getPobName(event.indexs.get(0));
        int prevIndex = event.indexs.get(0);
        for (int i = 1; i < event.indexs.size()-1; i++){
            index = event.indexs.get(i);
            if (index == prevIndex) continue;
            solution[i] = model.getPobName(index);
            prevIndex = index;
        }
        solution[event.indexs.size()-1] = model.getPobName(event.indexs.get(event.indexs.size()-1));
        
        this.solutionList.setListData(solution);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonStart;
    private javax.swing.JRadioButton destRadio;
    private javax.swing.JCheckBox distanceBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelDestSelected;
    private javax.swing.JLabel labelOriginSelected;
    private javax.swing.JLabel labelPassingSelected;
    /*
    private javax.swing.JPanel map;
    */MapDisplay map;
    private javax.swing.JComboBox<String> mapComboBox;
    private javax.swing.JCheckBox moneyBox;
    private javax.swing.JRadioButton originRadio;
    private javax.swing.JRadioButton passingRadio;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JList<String> solutionList;
    private javax.swing.JComboBox<String> structureComboBox;
    private javax.swing.JCheckBox timeBox;
    // End of variables declaration//GEN-END:variables
}
