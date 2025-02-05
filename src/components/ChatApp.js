import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const ChatApp = () => {
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
    const client = Stomp.over(socket);

    client.connect({}, () => {
      client.subscribe("/topic/public", (msg) => {
        const newMessage = JSON.parse(msg.body);
        setMessages((prev) => [...prev, newMessage]);
      });
    });

    setStompClient(client);
    return () => client.disconnect();
  }, []);

  const sendMessage = () => {
    if (stompClient) {
      const chatMessage = { sender: "User1", content: message, type: "CHAT" };
      stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
      setMessage("");
    }
  };

  return (
    <div>
      <h2>채팅</h2>
      <div>
        {messages.map((msg, index) => (
          <div key={index}>
            <strong>{msg.sender}: </strong> {msg.content}
          </div>
        ))}
      </div>
      <input value={message} onChange={(e) => setMessage(e.target.value)} />
      <button onClick={sendMessage}>전송</button>
    </div>
  );
};

export default ChatApp;
