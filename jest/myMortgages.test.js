
const axios = require('axios');
const FormData = require('form-data');

describe('My Mortgages', () => {
  it('can be displayed', async () => {
    const token = await getTokenAsync();
    let options = {
      headers: {
        Authorization: token,
      }
    }
    
    url = HIPPOTECH_URL + '/api/approval';
    result = await axios.get(url, options);
    expect(result.data.length).toBeGreaterThan(0);
  })
})
