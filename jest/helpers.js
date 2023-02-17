const axios = require('axios');

global.HIPPOTECH_URL ="http://localhost:3001";

global.hippotechToken = "";

global.getTokenAsync = async function() {
  if (global.hippotechToken === "") {
    const username = "siguser@synopsys.com";
    const password = "password";
    const url = global.HIPPOTECH_URL + '/api/authenticate';

    const result = await axios.post(url, { username, password});

    if (result.status === 200) {
      this.authToken = result.data.token;
      this.username = username;

      const url = global.HIPPOTECH_URL + '/api/heartbeat';
      const options = {
        headers: {
          Authorization: this.authToken,
        }
      }
      await axios.get(url, options);
      global.hippotechToken = this.authToken;
      return this.authToken;
    }
    return "";
  } else {
    return global.hippotechToken;
  }
}