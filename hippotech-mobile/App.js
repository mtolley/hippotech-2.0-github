import 'react-native-gesture-handler';
import * as SplashScreen from 'expo-splash-screen';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';

SplashScreen.preventAutoHideAsync();
setTimeout(SplashScreen.hideAsync, 3000);

const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: 'purple',
    accent: 'yellow',
  },
};

import server from './server';
import HomeScreen from './HomeScreen';
import BlogScreen from './BlogScreen';

server.loginAsync('siguser@synopsys.com', 'password').then(() => console.log('Logged in.'));

const GetAMortgageScreen = props => (
  <View style={styles.container}>
        <Text>Sign Me Up!</Text>
        <StatusBar style="auto" />
  </View>
)

const MyMortgagesScreen = props => (
  <View style={styles.container}>
        <Text>My Mortgages</Text>
        <StatusBar style="auto" />
  </View>
)

const Drawer = createDrawerNavigator();

export default function App() {
  return (
    <PaperProvider theme={theme}>
    <NavigationContainer>
      
      <Drawer.Navigator>
        <Drawer.Screen name="Home" component={HomeScreen} />
        <Drawer.Screen name="Get A Mortgage" component={GetAMortgageScreen} />
        <Drawer.Screen name="My Mortgages" component={MyMortgagesScreen} />
        <Drawer.Screen name="Blog" component={BlogScreen} />
      </Drawer.Navigator>

    </NavigationContainer>
    </PaperProvider>
    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  }
});
