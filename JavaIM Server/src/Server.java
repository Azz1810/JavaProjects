import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	// constructor
	public Server() {
		super("Azz's IM - SERVER");
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendMessage(event.getActionCommand());
				userText.setText("");
			}
		});
		add(userText, BorderLayout.NORTH);

		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow));
		setSize(640, 320);
		setVisible(true);
	}

	// set up and run the server
	public void startRunning() {
		try {
			server = new ServerSocket(6789, 100);
			while (true) {
				try {
					// Connect and start IM conversation
					waitForConnection();
					setupStreams();
					whileChatting();
				} catch (EOFException eofException) {
					showMessage("\nServer ended the connection!");
				} finally {
					//closeConnection();
					showMessage("Connections closed...");
					connection.shutdownInput();
					//setupStreams();
					waitForConnection();
					
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// wait for connection then display connection information
	private void waitForConnection() throws IOException {
		showMessage("Waiting for connection...");
		connection = server.accept();
		showMessage("\nConnected to " + connection.getInetAddress().getHostAddress());
	}

	// get the stream to send and receive data
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nStreams are setup");
	}

	// during the chat conversation
	private void whileChatting() throws IOException {
		String message = "You are now connected\n";
		sendMessage(message);
		ableToType(true);
		do {
			// conversation is active
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("\nUser error");
			}
		} while (!message.equals("CLIENT - END"));
	}

	// close streams and sockets
	private void closeConnection() {
		showMessage("\nClosing connections...");
		//ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// send a message to client
	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\nSERVER - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\nERROR: Cannot send message");
		}

	}

	// updates chatWindow
	private void showMessage(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				chatWindow.append(message);
			}
		});
	}

	// let the user type
	private void ableToType(final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				userText.setEditable(b);
			}
		});
	}
}