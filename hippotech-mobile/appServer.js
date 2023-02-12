
import axios from 'axios';
import { logError } from './utils';

// The React Native app is hardcoded to talk to http://10.0.2.2:3001 which is
// localhost when running in the Android simulator. i.e. the machine you're
// running the emulator on. Therefore it will not current work on the iOS
// simulator.
const host = 'http://10.0.2.2:3001';

export default class AppServer {
  constructor() {
    this.authToken = null;
    this.username = null;
  }

  isLoggedIn() { return !!this.authToken; }

  async loginAsync(username, password) {  
    console.log(username);
    console.log(password);

    const result = await axios.post(host + '/api/authenticate', { username, password});
    console.log(result.data);

    if (result.status === 200) {
      this.authToken = result.data.token;
      this.username = username;
      return true;
    }
    return false;
  }

  async withdrawApplication(id) {
    const url = host + '/api/approval/' + id;
    const options = {
      headers: {
        Authorization: this.authToken,
      }
    }
    await axios.delete(url, options);
  }


  async logoutAsync() {
    console.log('logoutAsync');
    this.authToken = null;
    this.username = null;
    return Promise.resolve();
  }

  async getMyMortgagesAsync() {
    if (!this.username) {
      logError('Calling getMyMortgagesAsync while not logged in.');
      return Promise.resolve([]);
    }

    const url = host + '/api/approval';
    const options = {
      headers: {
        Authorization: this.authToken,
      }
    }
    const result = await axios.get(url, options);
    console.log("Got mortgagers from API: ", result.data);
    return Promise.resolve(result.data);
  }

  async postFraudCheck(cardnumber) {
    const bodyFormData = new FormData();
    bodyFormData.append("cardnumber", cardnumber);
    const url = host + '/api/fraudcheck';
    const options = {
      headers: {
        Authorization: this.authToken,
        "Content-Type": "multipart/form-data"
      },
      data: bodyFormData,
    }
    const result = await axios.post(url, bodyFormData, options);
    console.log(result);
  }

  async requestApproval(approvalDetails) {
    console.log(approvalDetails);
    console.log(approvalDetails);
    await this.postFraudCheck(approvalDetails.cardNumber);
    const url = host + '/api/approval';
    const options = {
      headers: {
        Authorization: this.authToken,
      }
    }
    await axios.post(url, approvalDetails, options);
  }

  async submitCommentAsync(postId, text) {
    const url = host + '/api/mobile/blog/' + postId + '/comments';
    const options = {
      headers: {
        Authorization: this.authToken,
        'Content-Type': 'text/plain'
      }
    }
    try {
      await axios.post(url, text, options);
    } catch (error) {
      console.log(error.response);
      return error.response.data;
    }
  }

  async submitJustification(post, justification) {
    const url = host + '/api/mobile/blog/justification';
    const options = {
      headers: {
        Authorization: this.authToken,
      }
    }
    try {
      await axios.post(url, { post, justification }, options);
    } catch (error) {
      console.log(error.response);
      return error.response.data;
    }
  }

  async subscribeToBlog(email, partnersIncluded) {
    const url = host + '/api/mobile/blog/subscribe';
    const data = {
      email,
      partnersIncluded
    };
    try {
      await axios.post(url, data);   
    } catch (e) {
      logError('suscribeToBlog() POST failed');
      logError(e);
    }
    
  }

  async getBlogPostsAsync() {
    let blogs = await axios.get(host + '/api/mobile/blog');
    return Promise.resolve(Array.from(blogs.data));
  }

  async getBlogPostAsync(id) {
    let blogs = await axios.get(host + '/api/mobile/blog');
    let extractedBlog = null;
    blogs.data.forEach(blog => {
      if (blog.id === id) {
        extractedBlog = blog;
      }
    });
    return extractedBlog;
  }
}