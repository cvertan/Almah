package allmahVer4.graphdb;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Simple credentials dialog for GraphDB. */
public class GraphDBConnectionDialog extends JDialog {

    private final GraphDBConfig config;
    private boolean connected = false;
    private JTextField userField;
    private JPasswordField passwordField;

    public GraphDBConnectionDialog(JFrame owner, GraphDBConfig config) {
        super(owner, "Connect to GraphDB", true);
        this.config = config;
        build();
    }

    private void build() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("GraphDB URL:"), gbc);
        gbc.gridx = 1;
        form.add(new JLabel(config.getServerUrl()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Repository:"), gbc);
        gbc.gridx = 1;
        form.add(new JLabel(config.getRepositoryId()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        userField = new JTextField(25);
        if (config.getUsername() != null) {
            userField.setText(config.getUsername());
        }
        form.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(25);
        form.add(passwordField, gbc);

        JButton testButton = new JButton("Test Connection");
        JButton connectButton = new JButton("Connect");
        JButton cancelButton = new JButton("Cancel");

        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                testConnection(false);
            }
        });
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                testConnection(true);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(testButton);
        buttons.add(connectButton);
        buttons.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void testConnection(boolean closeOnSuccess) {
        config.setUsername(userField.getText());
        config.setPassword(new String(passwordField.getPassword()));
        try {
            new GraphDBWriter(config).testConnection();
            connected = true;
            JOptionPane.showMessageDialog(this,
                    "Connected to GraphDB repository " + config.getRepositoryId());
            if (closeOnSuccess) {
                dispose();
            }
        } catch (Exception ex) {
            connected = false;
            JOptionPane.showMessageDialog(this,
                    "Could not connect to GraphDB:\n" + ex.getMessage(),
                    "GraphDB connection failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConnected() {
        return connected;
    }
}