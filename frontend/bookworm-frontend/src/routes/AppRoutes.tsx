import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';


import LoginPage from "../pages/login/LoginPage";

const AppRoutes: React.FC = () => {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<LoginPage />} />         
          <Route path="/login" element={<LoginPage />} />         

        </Routes>
      </Router>
    );
  };

export default AppRoutes;
