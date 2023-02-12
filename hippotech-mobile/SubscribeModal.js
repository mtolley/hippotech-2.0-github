import * as React from 'react';
import { Modal, TextInput, Text, Button, ScrollView } from 'react-native-paper';

const SubscribeModal = (props) => {
  const containerStyle = {backgroundColor: 'white', padding: 20};
  const [text, setText] = React.useState("");
  
  const handleSubscribe = () => {
    props.onEmailSubmitted(text);
    setText("");
  }

  return (
        <Modal visible={props.visible} onDismiss={props.onDismiss} contentContainerStyle={containerStyle} style={{ marginHorizontal: 20 }} >
          <TextInput
            label="Email"
            value={text}
            onChangeText={text => setText(text)}
          />
          <Text>Get great stories from the wonderful world of properties and mortgages delivered directly to your inbox!</Text>
          <Button mode="contained" onPress={handleSubscribe}>Sign me up!</Button>
        </Modal>
  );
};

export default SubscribeModal;