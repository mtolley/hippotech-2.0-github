
const axios = require('axios');

describe('Homepage', () => {
  it('provides a link to Get Me A Mortgage', async () => {
    const response = await axios.get(HIPPOTECH_URL);
  })

  it('is logged out by default', async () => {
    const response = await axios.get(HIPPOTECH_URL);
  })
})
