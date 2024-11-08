import React, { useState } from "react";
import Header from "../../assets/Header";
import axios, { AxiosError } from "axios";
import '../login/LoginPage.css'


const RegisterPage: React.FC = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [image, setImage] = useState("");
    const [usernameError, setUsernameError] = useState("");
    const [emailError, setEmailError] = useState("");

    const handleUsernameData = async (username: string) => {
        try {
            await axios.post("http://localhost:8080/auth/check-username", null, { params: { username } });
            setUsernameError(""); 
        } catch (error: unknown) { 
            if (axios.isAxiosError(error)) { 
                if (error.response && error.response.status === 409) {
                    setUsernameError("Username already taken.");
                } else {
                    console.error("Error checking username:", error);
                }
            } else {
                console.error("Unexpected error:", error); 
            }
        }
    };
    
    const handleEmailData = async (email: string) => {
        try {
            await axios.post("http://localhost:8080/auth/check-email", null, { params: { email } });
            setEmailError(""); 
        } catch (error: unknown) {
            if (axios.isAxiosError(error)) {
                if (error.response && error.response.status === 409) {
                    setEmailError("Email already registered.");
                } else {
                    console.error("Error checking email:", error);
                }
            } else {
                console.error("Unexpected error:", error);
            }
        }
    };

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if (!usernameError && !emailError) { 
            try {
                const response = await axios.post("http://localhost:8080/auth/register", {
                    username, email, password, image
                });
                console.log("Successfully registered:", response.data);
            } catch (error) {
                console.error("Registration failed:", error);
            }
        }
    };

    return (
        <>
            <Header />
            <div className="conteiner">
            <form onSubmit={handleSubmit} className="forms">
            <div className="inputs">
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        onBlur={() => handleUsernameData(username)} 
                    />
                    {usernameError && <p style={{ color: "red" }}>{usernameError}</p>}
                </div>
                <div className="inputs">
                <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        onBlur={() => handleEmailData(email)} 
                        required
                    />
                    {emailError && <p style={{ color: "red" }}>{emailError}</p>}
                </div>
                <div className="inputs">
                <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
           
                <button type="submit">Register</button>
            </form>
            </div>
        </>
    );
};

export default RegisterPage;
