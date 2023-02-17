
const axios = require('axios');
const FormData = require('form-data');

describe('Mortgage Approval', () => {
  it('can be requested', async () => {
    // Login
    const token = await getTokenAsync();
    
    const cardNumber = '1111222233331234';

    // Fraud check
    const bodyFormData = new FormData();
    bodyFormData.append("cardnumber", cardNumber);
    let url = HIPPOTECH_URL + '/api/fraudcheck';
    let options = {
      headers: {
        Authorization: token,
        "Content-Type": "multipart/form-data"
      },
      data: bodyFormData,
    }
    let result = await axios.post(url, bodyFormData, options);
    
    // Submit approval request
    url = HIPPOTECH_URL + '/api/approval';
    const approvalDetails = {
      address1: "11 Park Plein",
      address2: "Lower Voorburg", 
      zip: "12345", 
      state: "California", 
      purchasePrice: '655000', 
      amountToBorrow: '255000',
      cardName: "Mr Scott Tolley",
      cardNumber: "1111222233331234",
      expDate: "02/26",
      cvv: "999"
    };

    options = {
      headers: {
        Authorization: token
      }
    }
    let response = await axios.post(url, approvalDetails, options);
    const newRequestId = response.data.id;

    // Check that creation was successful
    url = HIPPOTECH_URL + '/api/approval';
    
    result = await axios.get(url, options);
    
    let foundNewId = false;

    for (const approval of result.data) {
      if (approval.id === newRequestId) {
        foundNewId = true;
      }
    }

    expect(foundNewId).toBe(true);
  })
})
