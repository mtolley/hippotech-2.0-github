
const axios = require('axios');

describe('Blog', () => {
  it('can be viewed without authentication', async () => {
    response = await axios.get(HIPPOTECH_URL + '/api/blog');
    
  })
})

describe('Blog', () => {
  it('can be subscribed to', async () => {
    const url = HIPPOTECH_URL + "/api/blog/subscribe";
    const data = {
      email: "siguser@synopsys.com" + Date.now().toString(),
      partnersIncluded: true
    };
    try {
      const response = await axios.post(url, data);   
    } catch (e) {
      console.error('suscribeToBlog() POST failed');
      console.error(e);
      throw e;
    }
    
  })
})

describe('Blog', () => {
  it('can be commented on once authenticated', async () => {
    
    // Log in
    const token = await getTokenAsync();
    
    // Get the blogs
    await axios.get(HIPPOTECH_URL + '/api/blog');

    const url = HIPPOTECH_URL + '/api/blog/2/comments';
    const options = {
      headers: {
        Authorization:token,
        'Content-Type': 'text/plain'
      }
    }
    try {
      await axios.post(url, "A regular, inoffensive comment", options);
    } catch (error) {
      console.log(error.response);
      throw error;
    }
  })
})

describe('Blog', () => {
  it('will not allow rude words in the comments', async () => {
    // Log in
    const token = await getTokenAsync();
  
    // Get the blogs
    await axios.get(HIPPOTECH_URL + '/api/blog');

    const url = HIPPOTECH_URL + '/api/blog/2/comments';
    const options = {
      headers: {
        Authorization:token,
        'Content-Type': 'text/plain'
      }
    }
    try {
      await axios.post(url, "What an idiot!", options);
      
      // We shouldn't get here
      expect(1).toBe(0);
    } catch (error) {
      expect(error.response.status).toBe(400);
    }
  })
})
