import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';


import LoginPage from "../pages/login/LoginPage";
import Home from "../pages/Home";
import RegisterPage from "../pages/register/RegisterPage";

const AppRoutes: React.FC = () => {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />         
          <Route path="/login" element={<LoginPage />} />         
          <Route path="/register" element={<RegisterPage />} />         
        </Routes>
      </Router>
    );
  };

export default AppRoutes;
