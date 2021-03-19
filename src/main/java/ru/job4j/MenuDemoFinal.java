package ru.job4j;

import javax.swing.*;
import java.awt.event.*;

public class MenuDemoFinal implements ActionListener {
    JLabel jlab;
    JMenuBar jmb;
    JToolBar jtb;
    JPopupMenu jpu;

    DebugAction setAct;
    DebugAction clearAct;
    DebugAction resumeAct;

    MenuDemoFinal() {
        //создать новый контейнер типа JFrame
        JFrame jFrame = new JFrame("Complete Menu Demo");
        // Полная демонстрация меню
        //использовать граничную компоновку,
        // выбираемую по умолчанию
        // задать исходные размеры фрейма
        jFrame.setSize(360, 200);
        //завершить прикладную программу, когда
        // пользователь закроет ее окно
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //создать метку для отображения результатов выбора из меню
        jlab = new JLabel();
        //создать строку меню
        jmb = new JMenuBar();
        //создать меню File
        makeFileMenu();
        //создать действия отладки
        makeActions();
        //создать панель инструментов
        makeToolBar();
        //создать меню Options
        makeOptionsMenu();
        //создать меню Help
        makeHelpMenu();
        //создать меню Edit
        makeEditPUМenu();

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    jpu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    jpu.show(e.getComponent(), e.getX() ,e.getY());
                }
            }
        });

        //ввести метку в центре панели содержимого
        jFrame.add(jlab, SwingConstants.CENTER);
        // ввести панель инструментов в северном положении
        //панели содержимого
        jFrame.setJMenuBar(jmb);
        //отобразить фрейм
        jFrame.setVisible(true);
    }
    //Обработать события действия от пунктов меню
    //Здесь не обрабатываются события ,генерируемые
    //при выборе режимов отладки в подменю или на панели
    //инструментов Debug
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    static class DebugAction extends AbstractAction {
        public DebugAction(String name, Icon image, int mnem, int accel, String tTip) {
            super(name, image);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, new Integer(mnem));
            putValue(SHORT_DESCRIPTION, tTip);
        }

        //обработать события как и на панели инструментов
        //так и в подменю Debug
        @Override
        public void actionPerformed(ActionEvent e) {
            String comstr = e.getActionCommand();

            
            
        }
    }

    void makeFileMenu() {
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiClose = new JMenuItem("Close", KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        // ввести приемники действий для пунктов меню File
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
    }

    // создать меню Options
    void makeOptionsMenu() {
        JMenu jmOptions = new JMenu("Options");
        // создать подменю Colors
        JMenu jmColors = new JMenu("Colors");
        // использовать флажки, чтобы пользователь мог
        // выбрать сразу несколько цветов
        JCheckBoxMenuItem jmiRed =
                new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen =
                new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue =
                new JCheckBoxMenuItem("Blue");
        // ввести пункты в подменю Colors
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);
        // создать подменю Priority
        JMenu jmPriority = new JMenu("Priority");
        //Использовать кнопки -переключатели для установки
        // приоритета.Благодаря этому в меню не только
        // отображается установленный приоритет, но и
        // гарантируется установка одного и только одного
        // приоритета.Исходно выбирается кнопка - переключатель
        // в пункте меню High
        JRadioButtonMenuItem jmiHigh =
                new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow =
                new JRadioButtonMenuItem("Low");
        // ввести пункты в подменю Priority
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);
        //создать группу кнопок - переключателей
        // в пунктах подменю Priority
        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);
        //создать подменю Debug, входящее
        // в меню Options, используя действия
        // для создания пунктов этого подменю
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);
        // ввести пункты в подменю Debug
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);
        // создать пункт меню Reset
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);
        // И наконец, ввести все выбираемые меню в строку меню
        jmb.add(jmOptions);
        //ввести приемники действий для пунктов меню Options,
        // кроме тех, что поддерживаются в подменю Debug
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
    }

    //создать меню
    //Help

    void makeHelpMenu() {
        JMenu jmНelp = new JMenu("Help");
        // ввести значок для пункта меню About
        ImageIcon icon = new ImageIcon("Abouticon.gif");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setToolTipText(
                "Info about the MenuDemo program.");
        jmНelp.add(jmiAbout);
        jmb.add(jmНelp);
        //ввести приемник действий для пункта меню About
        jmiAbout.addActionListener(this);
    }
        //создать действия для управления подменю и
        //панелью инструментов Debug
        void makeActions() {
            //загрузить изображения для обозначения действий
            ImageIcon setIcon = new ImageIcon("setBP.gif");
            ImageIcon clearIcon = new ImageIcon("clearBP.gif");
            ImageIcon resumeIcon = new ImageIcon("resume.gif");
            // создать действия
            setAct = new DebugAction("Set Breakpoint",
                    setIcon, KeyEvent.VK_S,
                    KeyEvent.VK_B,
                    "Set а break point.");
            clearAct = new DebugAction("Clear Breakpoint",
                    clearIcon,KeyEvent.VK_C,
                    KeyEvent.VK_L,
                    "Clear break point");
            resumeAct = new DebugAction("Resume", resumeIcon,
                    KeyEvent.VK_R, KeyEvent.VK_R,
                    "Resume execution after breakpoint.");
            //сделать первоначально недоступным вариант
            // выбора Clear Breakpoint
            clearAct.setEnabled(false);
        }
        // создать панель инструментов Debug
        void makeToolBar () {
            // создать кнопки для панели инструментов,
            //используя соответствующие действия
            JButton jbtnSet = new JButton(setAct);
            JButton jbtnClear = new JButton(clearAct);
            JButton jbtnResume = new JButton(resumeAct);
            //создать панель инструментов Debug
            jtb = new JToolBar("Breakpoints");
            // ввести кнопки на панели инструментов
            jtb.add(jbtnSet);
            jtb.add(jbtnClear);
            jtb.add(jbtnResume);
        }

        // создать всплывающее меню Edit
        void makeEditPUМenu () {
            jpu = new JPopupMenu();
            // создать пункты всплывающего меню Edit
            JMenuItem jmiCut = new JMenuItem("Cut");
            JMenuItem jmiCopy = new JMenuItem("Сору");
            JMenuItem jmiPaste = new JMenuItem("Paste");
            // ввести пункты во всплывающее меню Edit
            jpu.add(jmiCut);
            jpu.add(jmiCopy);
            jpu.add(jmiPaste);
            //ввести приемники действий для
            // всплывающего меню Edit
            jmiCut.addActionListener(this);
            jmiCopy.addActionListener(this);
            jmiPaste.addActionListener(this);
        }
}







