# Secure-Chat-Room-with-SSL-Certification
**My goal was to create the secure chat room for our clients to connect with the server. The server is made to handle multiple connections at the same time. The project is made using JAVA**

•	But in order to make the messages secure we have to use the SSL socket instead of the normal socket
•	SSL SOCKET: SSL (Secure Sockets Layer) is a protocol for establishing secure links between networked computers. It is used to ensure the confidentiality, integrity, and authenticity of data transmitted over the internet. When a client (e.g. a web browser) establishes a connection to a server (e.g. a website), SSL ensures that all data transmitted between the client and server is encrypted and can only be decrypted by the intended recipient. This helps protect against eavesdropping and tampering with the data. SSL is an important security measure for protecting sensitive information, such as login credentials and credit card numbers, when it is transmitted over the internet.
•	In order to use the ssl socket we first have to create the keystore and the truststore
•	A Java Keystore is a file that contains certificates and private keys that are used for SSL/TLS communication. It is used to store and manage the security certificates and keys that are used to secure network communication. The Java Keystore is protected by a password and is used to store the certificates and keys for SSL/TLS communication.
•	1 - First Generate the server Certificate and public/private key and store it in keystore file
keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias mykey -keystore myKeyStore.jks

•	A Java Truststore is similar to a Keystore, but it contains only trusted certificates. It is used to establish trust in the certificates that are used for SSL/TLS communication. When a client (e.g. a web browser) establishes a connection to a server (e.g. a website), it will use the Truststore to verify the server's certificate. If the certificate is not trusted, the client will not establish a connection to the server.
•	2 - Export the certficate and the public key that should be send to the client.
 keytool -export -alias mykey -keystore myKeyStore.jks -file mykey.cert

•	3 - Add the key at the client side to a TrustedStore to trust the server
keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts

•	I have given you these files so you don’t have to generate the files again.
•	And using the system.setproperty I have given the path of my keystore in server side and truststore in client side.








MAKING THE SECURE SOCKET:
•	For making a ssl socket we use SSLSocketFactory which is a class that is provided to use by the javax.net.ssl library.
•	We make a new object of the SSLSocketFactory.
•	SSLSocketFactory is a class in the Java Secure Socket Extension (JSSE) that is used to create SSL/TLS sockets. It is a factory for creating SSL/TLS sockets, which are used to establish secure connections between networked computers. SSLSocketFactory is implemented by the javax.net.ssl.SSLSocketFactory class and provides a set of methods for creating SSL/TLS sockets and configuring their parameters.
•	
•	To create an SSL/TLS socket using SSLSocketFactory, you can use the createSocket method, which takes a hostname and port number as arguments and returns a new SSL/TLS socket that is connected to the specified host on the specified port. You can also use the setSSLParameters method to configure the SSL/TLS parameters for the socket, such as the cipher suites and protocols that are enabled.
•	
•	Overall, SSLSocketFactory is a useful class for creating SSL/TLS sockets and configuring their parameters in Java. It is often used in conjunction with the Java Keystore and Truststore to manage the security certificates and keys that are used for SSL/TLS communication.


CONNECTION WITH CLIENT
•	To connect with the client we will listen for the client using socket.accept()
•	We will do this in a loop so we can connect to multiple clients
•	Once a client is connected we will make the socket with the client with its connection then we will make the object of our client handler class and send the socket that we created with the client connection to the constructor of the class.
•	We will take the object and call the multi threaded function of the client handler class that takes the input from the particular client and broadcast the output to all the client that are connected to the chat server. And then the whole process will repeat from the first bullet.
•	Inside the client handler class we are saving the username and the socket of the client in an array.
•	The broadcast function which send one client message to all the client in the chat room , takes the socket of the all the other client using the array in which we are saving the sockets of all the clients and open the output stream of their socket then using the PrintWriter we the data to all the client
•	The multi threaded function Which name is run is a runnable function runs on another thread and is able to read the data that client sends us using the bufferReader. And then it calls the broadcast function which name is ECHO.


CLIENT SIDE
•	First we create the ssl socket as explained earlier then we will give the path to our trust store in the system.property
•	We will connect with the client using the port of the server.
•	Then in here we have two function on is multi threaded and another runs on the main thread
•	RECEIVE DATA : in this function it a multi threaded function in which we will read the data that server might send for this we will use the BufferReader object and then read the data using readLine() and then print the data. This will run in a loop until client is terminated
•	SEND DATA : This program we will use it to send the data to the client and in this function we will take input from the user and it will also run in loop . once user gave us the input we will use the PrintWriter object and then send the data.

