import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image } from 'react-native';
import { Avatar, Button, Card, Title, Paragraph } from 'react-native-paper';
import server from './server';
import React from 'react';
import SubscribeModal from './SubscribeModal';

const images = {
  "blog1.jpg" : require("./assets/blog1.jpg"),
  "blog2.jpg" : require("./assets/blog2.jpg"),
  "blog3.jpg" : require("./assets/blog3.jpg"),
  "blog4.jpg" : require("./assets/blog4.jpg"),
  "blog5.jpg" : require("./assets/blog5.jpg"),
  "blog6.jpg" : require("./assets/blog6.jpg")
};

const MyComponent = (props) => (
  <View style={{height: 200}}>
    <Card onPress={() => console.log("Press!")}>
    <Card.Cover source={images[props.image]} />
      <Card.Content>
        <Title>{props.title}</Title>
      </Card.Content>     
    </Card>
  </View>
);

const MyB = () => (
  <Button>One Item</Button>
)

const BlogScreen = props => {
  let [posts, setPosts]= React.useState([]);
  const [subscribeModalOpen, setSubscribeModalOpen] = React.useState(false);

  const handleSubscribe = () => {
    setSubscribeModalOpen(true);
  }

  const handleEmailSubmitted = email => {
    setSubscribeModalOpen(false);
    console.log(email);
    server.subscribeToBlog(email, false).then(() => console.log("Email submitted to Blog API"));
  }

  const handleDismiss = () => {
    setSubscribeModalOpen(false);
  }

  React.useEffect(() => {
    async function loadData() {
      const result = await server.getBlogPostsAsync();
      console.log(result);
      setPosts(result);
    }
    loadData();
  }, []);

  return (<View style={styles.container}>
    {posts.map((post, i) => (<MyComponent title={post.title} image={post.image} content="." key={i} />))} 
        <Button mode="contained" onPress={handleSubscribe}>Subscribe</Button>
        <StatusBar style="auto" />
        <SubscribeModal visible={subscribeModalOpen} onDismiss={handleDismiss} onEmailSubmitted={handleEmailSubmitted}/>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    justifyContent: "space-around", 
    backgroundColor: '#fff',
    alignItems: 'center'
  },
  item: {
    paddingHorizontal: 20
  },
  card: {
    
  },
  button: {
    
  }
});


export default BlogScreen;