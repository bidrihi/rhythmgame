package rhythm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
    LoginDAO loginDB = new LoginDAOImple();
    DynamicBeat dynamicBeat = new DynamicBeat(loginDB);
    JPanel jp = new JPanel();
    JButton jbtnLogin;
    JButton jbtnRegister;

    JTextField jtfID;
    JPasswordField jtfPW;
    JLabel jlbID, jlbPW;

    private boolean loginResult;

    public void loginWindow() {
        jp.setLayout(null);

        jlbID = new JLabel("ID");
        jlbID.setBounds(180, 50, 50, 20);
        jp.add(jlbID);

        jlbPW = new JLabel("Password");
        jlbPW.setBounds(150, 100, 60, 20);
        jp.add(jlbPW);

        jtfID = new JTextField();
        jtfID.setBounds(230, 50, 100, 20);
        jp.add(jtfID);

        jtfPW = new JPasswordField();
        jtfPW.setBounds(230, 100, 100, 20);
        jp.add(jtfPW);

        jbtnLogin = new JButton("login");
        jbtnLogin.setBounds(150, 175, 100, 50);
        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDB.setLoginID(jtfID.getText());
                loginDB.setLoginPW(String.valueOf(jtfPW.getPassword()));

                if (loginDB.loginStart()) {
                    // 한개의 창만 닫기 : 현재 창만 닫음
                    dispose();

                    // DynamicMusic 게임 시작
                    dynamicBeat.musicStart();
                } else {
                    System.out.println("로그인 실패");
                    JOptionPane.showConfirmDialog(jlbID, "로그인 정보를 확인해주세요", "로그인 에러", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
        jp.add(jbtnLogin);

        jbtnRegister = new JButton("회원가입");
        jbtnRegister.setBounds(300, 175, 100, 50);
        jbtnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(loginDB);
                dispose();
            }
        });
        jp.add(jbtnRegister);

        add(jp);

        setTitle("Login");
        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setFocusable(true);
    }
}
