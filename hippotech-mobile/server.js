import FakeServer from "./fakeServer";
import AppServer from "./appServer";

// let theServer;
// if (!process.env.FAKESERVER) {
//   console.log('server.js is constructing the server at: ' + process.env.REACT_APP_SERVER_URL);
//   theServer = new AppServer();
// } else {
//   console.log('server.js is constructing FakeServer');
//   theServer = new FakeServer();
// }

// const theServer = new FakeServer();
const theServer = new AppServer();

export default theServer;


