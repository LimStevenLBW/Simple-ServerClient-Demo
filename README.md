# Simple-ServerClient-Demo
This is a simple demonstration of a client-server app.

# Java Client and Server
Both applications are run through the command line. Arguments can be piped or entered through the terminal when prompted.
Compile the source code using Javac and run with the Java command.

You will need the port number and hostname or ip address of the server you want to connect to.
Run ipconfig /all to discover the hostname.

## Connectivity
You will run into some problems if you try to connect two different computers on separate networks.
Private IP addresses are assigned locally depending on the network and can be used for Lan connections. 

For online connectivity, use a public IP address, but that has some caveats.
Nowadays, everyone has a public IP address that is assigned by their ISP. 
This address is not fixed. Additionally, providers block most inbound ports for security purposes.

### Public IP Address
"A public IP address is the address that is assigned to a computing device to allow direct access over the Internet. A web server, email server and any server device directly accessible from the Internet are candidate for a public IP address. A public IP address is globally unique, and can only be assigned to a unique device" - iplocation.net

### Private IP Address
"A private IP address is the address space allocated by InterNIC to allow organizations to create their own private network. There are three IP blocks (1 class A, 1 class B and 1 class C) reserved for a private use. The computers, tablets and smartphones sitting behind your home, and the personal computers within an organizations are usually assigned private IP addresses." - iplocation.net
