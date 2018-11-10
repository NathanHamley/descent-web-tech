# descent-web-tech
A Test to make a borad game using a java server and vuejs frontend

# Start Everything
## Server
The server is a java Springboot application. Call mvn clean install in the descent-server folder to build the project. There should be a runnable jar in the target directory after compiling. Another option is to run the main class (DescentServerApplication.java) from an IDE of your choice. By default the Server will use Port 8080 and throw an error if it is not available. For now, due to cross origin constraints, the frontend should be started on localhost:8081.

## Frontend
The Frontend is a vue.js app, build using vue-cli. To run npm is required. For development Go to descent-frontend and call npm run serve. It is important to start the server first, as the frontend would also take port 8080, but adjusts to the nearest available port, most likely 8081. The frontend also expects the server to be available on localhost:8080 (for now).
