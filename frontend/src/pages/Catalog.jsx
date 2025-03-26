import React from 'react';

const Catalog = () => {
  const books = [
    { id: 1, title: 'The Great Gatsby', author: 'F. Scott Fitzgerald', price: '$10.99' },
    { id: 2, title: 'To Kill a Mockingbird', author: 'Harper Lee', price: '$8.99' },
    { id: 3, title: '1984', author: 'George Orwell', price: '$9.99' },
    { id: 4, title: 'Pride and Prejudice', author: 'Jane Austen', price: '$7.99' },
    { id: 5, title: 'The Catcher in the Rye', author: 'J.D. Salinger', price: '$6.99' },
  ];

  return (
    <div className="catalog-container">
      <h2>Book Catalog</h2>
      <div className="book-list">
        {books.map((book) => (
          <div key={book.id} className="book-item">
            <h3>{book.title}</h3>
            <p>Author: {book.author}</p>
            <p>Price: {book.price}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Catalog;