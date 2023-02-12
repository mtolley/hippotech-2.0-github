import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image, Button } from 'react-native';

const HomeScreen = props => (
  <View style={styles.container}>
        <Text style={[styles.item, styles.headline]}>HippoTech Mobile</Text>
        <Image style={{ width: "75%", height:undefined, aspectRatio:1 }} source={require('./assets/hippo-front.png')}></Image>
        <Text style={[styles.item, styles.byline]}>Don't waste your precious time filling in loads of boring old forms ... get an agreement in principle with HippoTech today!</Text>
        <Button color="purple" style={[styles.item, styles.button]} title="Get me a Mortgage!" />
        <StatusBar style="auto" />
  </View>
)

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "space-around", 
    backgroundColor: '#fff',
    alignItems: 'center'
  },
  item: {
    paddingHorizontal: 20
  },
  headline: {
    color: "purple",
    fontFamily: "sans-serif",
    fontSize: 30,
    fontWeight: "bold"
  },
  byline: {
    color: "black",
    fontStyle: "sans-serif",
    fontSize: 20,
    textAlign: "center"
  },
  button: {
    color: "purple"
  }
});


export default HomeScreen;