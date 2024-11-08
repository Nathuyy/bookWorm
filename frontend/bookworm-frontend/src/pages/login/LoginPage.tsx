import React, { useState } from "react";
import Header from "../../assets/Header";
import axios from "axios";
import './LoginPage.css'


const LoginPage: React.FC = () => {
  const [email,setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        email, password
      })

      const token = response.data;
      
      localStorage.setItem("jwtToken", token);

      setEmail("");
      setPassword("");

    } catch (error) {
      console.error(error);

    }
  }
    return (
     <div>
        <Header />
        <div className="conteiner">
          <form onSubmit={handleSubmit} className="forms">
            <div className="inputs">
              <label htmlFor="email">Email:</label>
              <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="your email here" required/>
            </div>
            <div className="inputs">
              <label htmlFor="password">Password:</label>
              <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="your password here" required/>
            </div>
              <button type="submit">Login</button>
          </form>
        </div>
     </div>
    );
  };


export default LoginPage;
