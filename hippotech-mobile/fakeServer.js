const mortgage1 = {
  id: 1,
  address1: '1 Alabaster Avenue',
  status: 'Approved',
  purchasePrice: 500000,
  amountToBorrow: 400000,
  history: [
    {
      id: 1,
      date: new Date(2020, 1, 5),
      party: 'Customer',
      event: 'Created',
      details: 'Submitted for approval'
    },
    {
      id: 2,
      date: new Date(2020, 2, 15),
      party: 'HippoTech',
      event: 'Agreement in Principal',
      details: 'Agreement in principal subject to lender checks and property valuation'
    }
  ]
};


const mortgage2 = {
  id: 2,
  address1: '35 Broad Street',
  status: 'Approval Pending',
  purchasePrice: 850000,
  amountToBorrow: 700000,
  history: [
    {
      id: 1,
      date: new Date(2020, 2, 17),
      party: 'Customer',
      event: 'Created',
      details: 'Submitted for approval'
    },
    {
      id: 2,
      date: new Date(2020, 4, 22),
      party: 'HippoTech',
      event: 'Agreement in Principal',
      details: 'Agreement in principal subject to lender checks and property valuation'
    }
  ]
};

const blogPost1 = {
  id: 1,
  title: 'Where next for house prices?',
  date: new Date(2021, 12, 13),
  description:
    "Boiling hot house prices in the Netherlands may be a sign of things to come in rich, densely populated countries.",
  image: 'blog1.jpg',
  imageText: 'main image description',
  fullText: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
  comments: [
    {
      email: "joost@ns.nl",
      text: "I could not disagree more. So I won't!"
    },
    {
      email: "xavierb@synopsys.com",
      text: "Je suis entièrement d'accord avec ce sentiment."
    }
  ]
};

const blogPost2 = 
  {
    id: 2,
    title: 'A Cornish treat',
    date: new Date(2022, 1, 1),
    description:
      'A small country estate nestled amongst 23 acres of rolling Cornish countryside, just a stone’s throw from Falmouth — for sale for £1.85 million',
    image: 'blog2.jpg',
    imageLabel: 'Image Text',
    fullText: 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.',
    comments: []
  };

const blogPost3 =
  {
    id: 3,
    title: 'How to become a ‘power buyer’ in 2022',
    date: new Date(2022, 1, 24),
    description:
      'Are you thinking of moving this year? Buyers are facing hot competition right now, with a huge number of people chasing after every available property for sale. So if you’ve been looking to move, you’ve no doubt noticed how competitive it is.',
    image: 'blog3.jpg',
    imageLabel: 'Image Text',
    fullText: 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\'t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.',
    comments: []
  };


async function fakeLatencyAsync() { 
  return new Promise(resolve => {
    setTimeout(() => resolve(), 1000);
  });
}

export default class FakeServer {
  constructor() {
    this.authToken = null;
    this.username = null;
    
    this.myMortgages = new Map();
    this.myMortgages.set(mortgage1.id, mortgage1);
    this.myMortgages.set(mortgage2.id, mortgage2);
    this.nextMortgageId = 3;

    this.blogPosts = new Map();
    this.blogPosts.set(blogPost1.id, blogPost1);
    this.blogPosts.set(blogPost2.id, blogPost2);
    this.blogPosts.set(blogPost3.id, blogPost3);
    this.nextBlogPostId = 4;

    console.log('FakesServer constructor');
  }

  isLoggedIn() { return !!this.authToken; }

  async loginAsync(username, password) {
    console.log('loginAsync');
    if (username === "siguser@synopsys.com" && password === "password") {
      this.authToken = "12345";
      this.username = "siguser@synopsys.com";
      return Promise.resolve(true);
    }
    return Promise.resolve(false);
  }

  async logoutAsync() {
    this.authToken = null;
    this.username = null;
    return Promise.resolve();
  }

  async getMyMortgagesAsync() {

    await fakeLatencyAsync();

    if (!this.username) {
      return Promise.resolve([]);
    }

    return Promise.resolve(Array.from(this.myMortgages, ([key, value]) => value));
  }

  async getUserAsync() {
    return Promise.resolve({
      email: "siguser@synopsys.com",
      title: "Mr",
      givenName: "Scott",
      familyName: "Tolley"
    });
  }

  async requestApproval(propertyDetails, cardDetails) {
    this.myMortgages.set(this.nextMortgageId, {
      id: this.nextMortgageId,
      address1: propertyDetails.address1,
      address2: propertyDetails.address2,
      zip: propertyDetails.zip,
      state: propertyDetails.state,
      purchasePrice: propertyDetails.purchasePrice,
      amountToBorrow: propertyDetails.amountToBorrow,
      status: "Approval Pending",
      history: [
        {
          id: 1,
          date: (new Date(Date.now())).toJSON(),
          party: 'Customer',
          event: 'Created',
          details: 'Submitted for approval'
        }
      ]
    });
    this.nextMortgageId++;
  }

  async withdrawApplication(id) {
    const mortgage = this.myMortgages.get(id);
    if (mortgage) {
      const newEventId = mortgage.history.length + 1;
      console.log("New ID: " + newEventId);
      mortgage.history.push({
        id: newEventId,
        date: new Date(),
        party: 'Customer',
        event: 'Application withdrawn',
        details: 'The application was withdrawn by request of the customer online'
      });
      mortgage.status = "Withdrawn"
    } else {
      throw new Error("Tried to withdraw application for non-existant mortgate: " + id);
    }
  }

  async submitJustification(justification) {
    console.log("Justification received.");
    return Promise.resolve();
  }

  async submitCommentAsync(postId, text) {
    const post = this.blogPosts.get(parseInt(postId));
    if (post !== undefined) {
      if (text.toUpperCase().includes('IDIOT')) {
        return { error: 'Bad Language', word: 'idiot' }
      }
      if (text.toUpperCase().includes('PIPEAU')) {
        return { error: 'Bad Language', word: 'pipeau' }
      }
      const user = await this.getUserAsync();
      post.comments.push({ email: user.email, text });
    } 

    return Promise.resolve();
  }

  async getBlogPostsAsync() {
    return Promise.resolve(Array.from(this.blogPosts, ([key, value]) => value));
  }

  async getBlogPostAsync(id) {
    return Promise.resolve(this.blogPosts.get(parseInt(id)));
  }

  async subscribeToBlog(email, partnersIncluded) {
    // The test server does nothing...
  }
}