/*
 * Copyright (C) 2016 Björn Büttner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.idrinth.waraddonclient.gui;

public class Window extends javax.swing.JFrame {

    private javax.swing.table.TableRowSorter sorter;
    private de.idrinth.waraddonclient.implementation.model.Addon activeAddon = null;
    private String language = "en";
    de.idrinth.waraddonclient.implementation.list.Tag tagList;

    /**
     * Creates new form Wrapper
     */
    public Window() {
        initComponents();
        finishGuiBuilding();
        processPosition();
    }

    public javax.swing.JTable getAddonTable() {
        return AddonList;
    }

    protected final void finishGuiBuilding() {
        AddonList.getSelectionModel().addListSelectionListener(new tableListener());
        Description.setText("<html><h1>Welcome to the client.</h1><p>To get something more useful here, select an addon to the left.</p>");
        InstallButton.setEnabled(false);
        RemoveButton.setEnabled(false);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/logo.png")));
        sorter = new javax.swing.table.TableRowSorter(AddonList.getModel());
        AddonList.setRowSorter(sorter);
        setTitle("Idrinth's WAR Addon Client");
        rightSide.setEnabledAt(1, false);
        Description.addHyperlinkListener(new hyperlinkListener());
        localVersion.setText(de.idrinth.waraddonclient.configuration.Version.getLocalVersion());

        tagList = new de.idrinth.waraddonclient.implementation.list.Tag(Tags);
        new java.lang.Thread(tagList).start();
        new java.lang.Thread(new de.idrinth.waraddonclient.configuration.Version()).start();
    }

    public void newFilter() {
        try {
            javax.swing.RowFilter rf = new de.idrinth.waraddonclient.gui.tablefilter.TextCategory(Search.getText(), tagList.getActiveTags());
            sorter.setRowFilter(rf);
        } catch (java.util.regex.PatternSyntaxException exception) {
            de.idrinth.factory.Logger.build().log(exception.getMessage(), de.idrinth.Logger.levelError);
        }
    }

    protected final void processPosition() {
        String errors = "";
        if (!new java.io.File("./WAR.exe").exists()) {
            errors += "Missing WAR.exe here, please put this file in the Warhammer Online directory.";
        }
        if (!new java.io.File("./RoRLauncher.exe").exists()) {
            errors += "Missing RoRLauncher.exe here, please put this file in the Warhammer Online directory.";
        }
        if (errors.length() > 0) {
            javax.swing.JOptionPane.showMessageDialog(this, errors);
            this.dispose();
            System.exit(0);
        }
    }

    class hyperlinkListener implements javax.swing.event.HyperlinkListener {

        public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent event) {
            if (javax.swing.event.HyperlinkEvent.EventType.ACTIVATED.equals(event.getEventType())) {
                try {
                    java.awt.Desktop.getDesktop().browse(event.getURL().toURI());
                } catch (java.net.URISyntaxException | java.io.IOException exception) {
                    de.idrinth.factory.Logger.build().log(exception.getMessage(), de.idrinth.Logger.levelError);
                }
            }

        }
    }

    public javax.swing.JLabel getRemoteVersionLabel() {
        return remoteVersion;
    }

    class tableListener implements javax.swing.event.ListSelectionListener {

        public void valueChanged(javax.swing.event.ListSelectionEvent event) {
            try {
                activeAddon = de.idrinth.waraddonclient.factory.AddonList.build().get(AddonList.convertRowIndexToModel(AddonList.getSelectedRow()));
            } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
                de.idrinth.factory.Logger.build().log(exception.getMessage(), de.idrinth.Logger.levelError);
                return;
            }
            if (activeAddon == null) {
                return;
            }
            Description.setText(activeAddon.getDescription(language));
            Title.setText(activeAddon.getName());
            InstallButton.setEnabled(true);
            RemoveButton.setEnabled(true);
            setTitle(activeAddon.getName() + " - Idrinth's WAR Addon Client");
            de.idrinth.waraddonclient.implementation.model.AddonSettings settings = activeAddon.getUploadData();
            rightSide.setEnabledAt(1, settings.showSettings());
            UploadReason.setText(settings.getReason());
            UploadUrl.setText(settings.getUrl());
            UploadFile.setText(settings.getFile());
            UploadEnable.setSelected(settings.isEnabled());
            String taglist = "Tagged: ";
            for (String tagname : activeAddon.getTags()) {
                taglist += tagname + ", ";
            }
            CurTags.setText(taglist.substring(0, taglist.length() - 2));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSplitPane2 = new javax.swing.JSplitPane();
        leftSide = new javax.swing.JPanel();
        Search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddonList = new javax.swing.JTable();
        rightSide = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Description = new javax.swing.JEditorPane();
        InstallButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        localVersion = new javax.swing.JLabel();
        remoteVersion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CurTags = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        UploadReason = new javax.swing.JTextArea();
        UploadUrl = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        UploadEnable = new javax.swing.JCheckBox();
        label1 = new java.awt.Label();
        UploadFile = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        English = new javax.swing.JRadioButtonMenuItem();
        Deutsch = new javax.swing.JRadioButtonMenuItem();
        Francais = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Refresh1 = new javax.swing.JCheckBoxMenuItem();
        Refresh2 = new javax.swing.JCheckBoxMenuItem();
        Refresh3 = new javax.swing.JCheckBoxMenuItem();
        Refresh4 = new javax.swing.JCheckBoxMenuItem();
        Tags = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        About = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setToolTipText("");
        jSplitPane2.setMinimumSize(new java.awt.Dimension(300, 200));
        jSplitPane2.setName(""); // NOI18N

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        jScrollPane2.setMaximumSize(null);

        AddonList.setAutoCreateRowSorter(true);
        AddonList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Version", "Installed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AddonList.setMaximumSize(null);
        AddonList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        AddonList.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(AddonList);
        AddonList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (AddonList.getColumnModel().getColumnCount() > 0) {
            AddonList.getColumnModel().getColumn(0).setResizable(false);
            AddonList.getColumnModel().getColumn(1).setResizable(false);
            AddonList.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout leftSideLayout = new javax.swing.GroupLayout(leftSide);
        leftSide.setLayout(leftSideLayout);
        leftSideLayout.setHorizontalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
            .addComponent(Search)
        );
        leftSideLayout.setVerticalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(leftSide);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(null);

        Description.setEditable(false);
        Description.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(Description);

        InstallButton.setText("(Re)Install");
        InstallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstallButtonActionPerformed(evt);
            }
        });

        RemoveButton.setText("Remove");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        Title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        localVersion.setText("0.0.0");
        localVersion.setToolTipText("Local Version");

        remoteVersion.setText("0.0.0");
        remoteVersion.setToolTipText("Remote Version");

        jLabel3.setText("/");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(InstallButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(RemoveButton)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(localVersion)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remoteVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CurTags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoveButton)
                    .addComponent(InstallButton)
                    .addComponent(Title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localVersion)
                    .addComponent(remoteVersion)
                    .addComponent(jLabel3)
                    .addComponent(CurTags))
                .addGap(6, 6, 6))
        );

        rightSide.addTab("Main", jPanel1);

        UploadReason.setEditable(false);
        UploadReason.setColumns(20);
        UploadReason.setRows(5);
        jScrollPane3.setViewportView(UploadReason);

        UploadUrl.setEditable(false);
        UploadUrl.setToolTipText("");

        jLabel1.setText("Upload URL");

        UploadEnable.setText("Allow Upload");
        UploadEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadEnableActionPerformed(evt);
            }
        });

        label1.setText("File to Upload");

        UploadFile.setEditable(false);
        UploadFile.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(UploadEnable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(UploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UploadEnable)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        rightSide.addTab("Settings", jPanel2);

        jSplitPane2.setRightComponent(rightSide);

        jMenu3.setText("Settings");

        jMenu1.setText("Language");

        English.setSelected(true);
        English.setText("English");
        English.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnglishActionPerformed(evt);
            }
        });
        jMenu1.add(English);

        Deutsch.setText("Deutsch");
        Deutsch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeutschActionPerformed(evt);
            }
        });
        jMenu1.add(Deutsch);

        Francais.setText("Francais");
        Francais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrancaisActionPerformed(evt);
            }
        });
        jMenu1.add(Francais);

        jMenu3.add(jMenu1);

        jMenu2.setText("Auto-Refresh");

        Refresh1.setText("15min");
        Refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh1ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh1);

        Refresh2.setText("30min");
        Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh2ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh2);

        Refresh3.setText("1h");
        Refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh3ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh3);

        Refresh4.setSelected(true);
        Refresh4.setText("3h");
        Refresh4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh4ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh4);

        jMenu3.add(jMenu2);

        Tags.setText("Tags");
        jMenu3.add(Tags);
        jMenu3.add(jSeparator1);

        About.setText("About");
        About.setToolTipText("");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        jMenu3.add(About);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InstallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstallButtonActionPerformed
        try {
            activeAddon.install();
            updateList();
            javax.swing.JOptionPane.showMessageDialog(this, "The requested Addon was installed.");
        } catch (java.lang.Exception exception) {
            de.idrinth.factory.Logger.build().log(exception.getMessage(), de.idrinth.Logger.levelError);
            javax.swing.JOptionPane.showMessageDialog(this, "Sadly Installing failed, check if the folder is writeable.");
        }
    }//GEN-LAST:event_InstallButtonActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        newFilter();
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        newFilter();
    }//GEN-LAST:event_SearchKeyReleased

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        activeAddon.uninstall();
        updateList();
    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void UploadEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadEnableActionPerformed
        activeAddon.getUploadData().setEnabled(UploadEnable.isSelected());
    }//GEN-LAST:event_UploadEnableActionPerformed

    private void EnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnglishActionPerformed
        changeLanguageTo("fr");
    }//GEN-LAST:event_EnglishActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AboutActionPerformed

    private void DeutschActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeutschActionPerformed
        changeLanguageTo("de");
    }//GEN-LAST:event_DeutschActionPerformed

    private void FrancaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrancaisActionPerformed
        changeLanguageTo("fr");
    }//GEN-LAST:event_FrancaisActionPerformed

    private void Refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh1ActionPerformed
        changeRefreshTo(15);
    }//GEN-LAST:event_Refresh1ActionPerformed

    private void Refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh2ActionPerformed
        changeRefreshTo(30);
    }//GEN-LAST:event_Refresh2ActionPerformed

    private void Refresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh3ActionPerformed
        changeRefreshTo(60);
    }//GEN-LAST:event_Refresh3ActionPerformed

    private void Refresh4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh4ActionPerformed
        changeRefreshTo(180);
    }//GEN-LAST:event_Refresh4ActionPerformed

    protected void changeLanguageTo(String lang) {
        if (lang.equals(language)) {
            return;
        }
        English.setSelected("en".equals(lang));
        Deutsch.setSelected("de".equals(lang));
        Francais.setSelected("fr".equals(lang));
        language = lang;
        if (activeAddon != null) {
            Description.setText(activeAddon.getDescription(language));
        }
    }

    protected void changeRefreshTo(int dur) {
        Refresh1.setSelected(dur == 15);
        Refresh2.setSelected(dur == 30);
        Refresh3.setSelected(dur == 60);
        Refresh4.setSelected(dur == 180);
        de.idrinth.waraddonclient.factory.AddonList.build().setDuration(dur);
    }

    protected void updateList() {
        for (int position = 0; position < AddonList.getRowCount(); position++) {
            if (de.idrinth.waraddonclient.factory.AddonList.build().get(AddonList.convertRowIndexToModel(position)).getName().equalsIgnoreCase(activeAddon.getName())) {
                AddonList.setValueAt(activeAddon.getInstalled(), position, 2);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JTable AddonList;
    private javax.swing.JLabel CurTags;
    private javax.swing.JEditorPane Description;
    private javax.swing.JRadioButtonMenuItem Deutsch;
    private javax.swing.JRadioButtonMenuItem English;
    private javax.swing.JRadioButtonMenuItem Francais;
    private javax.swing.JButton InstallButton;
    private javax.swing.JCheckBoxMenuItem Refresh1;
    private javax.swing.JCheckBoxMenuItem Refresh2;
    private javax.swing.JCheckBoxMenuItem Refresh3;
    private javax.swing.JCheckBoxMenuItem Refresh4;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JTextField Search;
    private javax.swing.JMenu Tags;
    private javax.swing.JLabel Title;
    private javax.swing.JCheckBox UploadEnable;
    private javax.swing.JTextField UploadFile;
    private javax.swing.JTextArea UploadReason;
    private javax.swing.JTextField UploadUrl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane2;
    private java.awt.Label label1;
    private javax.swing.JPanel leftSide;
    private javax.swing.JLabel localVersion;
    private javax.swing.JLabel remoteVersion;
    private javax.swing.JTabbedPane rightSide;
    // End of variables declaration//GEN-END:variables
}