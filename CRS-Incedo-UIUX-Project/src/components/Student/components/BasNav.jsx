import React, { useEffect, useState } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { LinkContainer } from 'react-router-bootstrap';
import { userstore } from '../../../redux/store';
import { useNavigate } from 'react-router-dom';
import styles from '../Student.module.css'

export const BasNav = () => {
  const nstore = userstore.getState()
  const navi = useNavigate()
  const startint = new Date()
  const [timer, setTimer] = useState(startint.toString().slice(4,21))

  const logout = e => {
    userstore.dispatch({ type: 'userinfo/logout' })
    navi('/')
  }

  useEffect(() => {
    let myNmare = setInterval(() => {
      let today = new Date()
      setTimer(today.toString().slice(4,21))
    }, 30000);

    return () => {
      clearInterval(myNmare)
    }
  }, [])

  return (
    <>
      <h1 style={{ textAlign: 'center' }}>Student Dashboard</h1>
      <Navbar bg="info" expand="lg">
        <Container>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <LinkContainer to="/">
                <Nav.Link>Home</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/student/register">
                <Nav.Link>Register</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/student/catalog">
                <Nav.Link>Catalog</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/student/course">
                <Nav.Link>Course</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/student/pay">
                <Nav.Link>Payment</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/student/notification">
                <Nav.Link>Alerts</Nav.Link>
              </LinkContainer>
            </Nav>
          </Navbar.Collapse>
          {nstore.loggedin &&
            <Navbar.Collapse className="justify-content-end">
              <Nav.Link>Welcome <strong>{nstore.name}</strong> Time: {timer}</Nav.Link>
              <LinkContainer to="/" onClick={logout}>
                <Nav.Link className={styles.Logout}>Logout</Nav.Link>
              </LinkContainer>
            </Navbar.Collapse>
          }
        </Container>
      </Navbar>
    </>
  )
}
