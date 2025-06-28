// App.jsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Catalog from './pages/Catalog';
// import BookDetails from './pages/BookDetails';
// import Order from './pages/Order';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/catalog" element={<Catalog />} />
        {/* <Route path="/book/:id" element={<BookDetails />} /> */}
        {/* <Route path="/order" element={<Order />} /> */}
      </Routes>
    </Router>
  );
}

export default App;
