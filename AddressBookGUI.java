import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

class Contact {
    private String name;
    private String address;
    private String phone;
    private String email;

    public Contact(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email;
    }
}

public class AddressBookGUI extends JFrame {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable contactTable;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;

    public AddressBookGUI() {
        setTitle("Address Book");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");

        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        nameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        for (Component component : inputPanel.getComponents()) {
            if (component instanceof JLabel) {
                ((JLabel) component).setFont(labelFont);
            } else if (component instanceof JTextField) {
                ((JTextField) component).setFont(inputFont);
            }
        }

        // Create a border with padding for the buttons
        EmptyBorder buttonPadding = new EmptyBorder(5, 10, 5, 10);

        // Create a color for the buttons
        Color buttonColor = new Color(41, 128, 185); // You can change the RGB values as needed

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });
        addButton.setBorder(buttonPadding);
        addButton.setBackground(buttonColor); // Set the background color

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });
        deleteButton.setBorder(buttonPadding);
        deleteButton.setBackground(buttonColor); // Set the background color

        JButton updateButton = new JButton("Update Contact");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
            }
        });
        updateButton.setBorder(buttonPadding);
        updateButton.setBackground(buttonColor); // Set the background color

        // Add a "Clear" button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        clearButton.setBorder(buttonPadding);
        clearButton.setBackground(buttonColor); // Set the background color

        // Add an "Exit" button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });
        exitButton.setBorder(buttonPadding);
        exitButton.setBackground(buttonColor); // Set the background color

        // Create a panel for the original buttons
        JPanel topButtonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        topButtonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        topButtonPanel.add(addButton);
        topButtonPanel.add(deleteButton);
        topButtonPanel.add(updateButton);

        // Create a panel for the new buttons
        JPanel bottomButtonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomButtonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        bottomButtonPanel.add(clearButton);
        bottomButtonPanel.add(exitButton);

        // Create a panel to hold both button panels
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(topButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addContact() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, address, phone, email);
            contacts.add(contact);
            tableModel.addRow(new String[]{name, address, phone, email});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedIndex = contactTable.getSelectedRow();
        if (selectedIndex != -1) {
            contacts.remove(selectedIndex);
            tableModel.removeRow(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateContact() {
        int selectedIndex = contactTable.getSelectedRow();
        if (selectedIndex != -1) {
            Contact selectedContact = contacts.get(selectedIndex);

            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (!name.isEmpty()) {
                selectedContact.setName(name);
                tableModel.setValueAt(name, selectedIndex, 0);
            }
            if (!address.isEmpty()) {
                selectedContact.setAddress(address);
                tableModel.setValueAt(address, selectedIndex, 1);
            }
            if (!phone.isEmpty()) {
                selectedContact.setPhone(phone);
                tableModel.setValueAt(phone, selectedIndex, 2);
            }
            if (!email.isEmpty()) {
                selectedContact.setEmail(email);
                tableModel.setValueAt(email, selectedIndex, 3);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddressBookGUI().setVisible(true);
            }
        });
    }
}
