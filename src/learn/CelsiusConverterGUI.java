package learn;

/**
 * Celsius
 * noun [ U ], adjective
 * UK  /ˈsel.si.əs/ US  /ˈsel.si.əs/
 * (also centigrade); (written abbreviation C)
 *
 * (of) a measurement of temperature on a standard in which 0° is the temperature at which water freezes, and 100° the temperature at which it boils
 * 摄氏温度
 * Are the temperatures given in Celsius or Fahrenheit?
 * 温度是用摄氏温度还是华氏温度标出的？
 *
 * Fahrenheit
 * adjective, noun [ U ]
 * UK  /ˈfær.ən.haɪt/ US  /ˈfer.ən.haɪt/
 * (written abbreviation F)
 *
 * (of) a measurement of temperature on a standard in which 32° is the temperature at which water freezes and 212° that at which it boils
 * 华氏的；华氏温标（冰点为32度，沸点为212度）
 *
 * temperature
 * noun
 * UK  /ˈtem.prə.tʃər/ US  /ˈtem.pɚ.ə.tʃɚ/
 * temperature noun (HEAT LEVEL)
 *
 * A2 [ C or U ]
 * the measured amount of heat in a place or in the body
 * 温度；体温
 *
 * 1、发明者不同。摄氏度：瑞典天文学家安德斯·摄尔修斯Anders Celsius。华氏度：德国人Gabriel D. Fahrenheit（华伦海特）。
 * 2、符号不同。摄氏度：℃。华氏度：°F。
 * 3、运用地域不同。摄氏度：中国等世界上很多国家使用摄氏度。。华氏度：美国和其他一些英语国家使用华氏度
 * 4、定义不同
 * 摄氏度：“把冰水混合物的温度设定为0℃，把沸水的温度设定为100℃，它们之间分成100等份，每一等份是摄氏度的一个单位，叫做1摄氏度。
 * 华氏度：在标准大气压下，冰的熔点为32℉，水的沸点为212℉，中间有180等分，每等分为华氏1度。
 */
public class CelsiusConverterGUI extends javax.swing.JFrame {

    /** Creates new form CelsiusConverterGUI */
    public CelsiusConverterGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        // 声明和定义分开
        // 组件命名方式，功能/作用+TextField/Label/Button
        // 命名，动词和名词可以随意使用，不会太纠结动词当成名词的问题
        tempTextField = new javax.swing.JTextField();
        celsiusLabel = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        fahrenheitLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Celsius Converter");

        celsiusLabel.setText("Celsius");

        convertButton.setText("Convert");
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        fahrenheitLabel.setText("Fahrenheit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(celsiusLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(convertButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fahrenheitLabel)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {convertButton, tempTextField});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(celsiusLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(convertButton)
                                        .addComponent(fahrenheitLabel))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
//Parse degrees Celsius as a double and convert to Fahrenheit
        int tempFahr = (int)((Double.parseDouble(tempTextField.getText()))
                * 1.8 + 32);
        fahrenheitLabel.setText(tempFahr + " Fahrenheit");
    }//GEN-LAST:event_convertButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CelsiusConverterGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel celsiusLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JLabel fahrenheitLabel;
    private javax.swing.JTextField tempTextField;
    // End of variables declaration//GEN-END:variables

}