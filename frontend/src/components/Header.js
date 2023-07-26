import React from 'react'
import HRCLogo from '../assests/hrclogo.svg'
import ABCLogo from '../assests/abclogo.svg'
const Header = () => {
    return (
        <><div className="logo">
            <span className="ABCLogo">
                <img src={ABCLogo} alt="ABC Logo" />
            </span>
            <span className="HRCLogo">
                <img src={HRCLogo} alt="HRC logo" />
            </span>
        </div>
            <div className="HeadingList">Invoice List</div>

        </>

    )
}

export default Header