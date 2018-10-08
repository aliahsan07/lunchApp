import React from 'react';

const Header = props => (
    <header className="top">
      <h2>
        Roti Khilao
      </h2>
      <h3 className="tagline">
        <span>{props.tagline}</span>
      </h3>
    </header>
  );
  
  export default Header;