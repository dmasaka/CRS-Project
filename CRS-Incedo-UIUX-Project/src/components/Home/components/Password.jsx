import React, { useState } from 'react'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import styles from '../Home.module.css'
import { useNavigate } from 'react-router-dom'


export const Password = () => {
  const navi = useNavigate()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [pass, setPass] = useState('')
  const [pass2, setPass2] = useState('')
  const [role, setRole] = useState('student')
  const [errbar, setErrbar] = useState('')
  const login = e => {
    e.preventDefault()
    if (pass === pass2) {
      fetch(import.meta.env.VITE_BACK + role + 's/password', {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json'
        }),
        body: JSON.stringify({
          username: username,
          password: password,
          newpassword: pass
        })
      })
        .then(resp => resp.json())
        .then(data => setTimeout(() => {
          console.log(data)
          navi("/")
        }, 2000))
    } else {
      setErrbar('Passwords do not match')
    }
  }
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <h1>Login</h1>
        <div className={styles.SerCont}>
          <Form className={styles.FormSin}>
            <Form.Group className="mb-3" controlId="formBasicText">
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} value={username} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicText">
              <Form.Label>Password</Form.Label>
              <Form.Control type="text" placeholder="Enter password" onChange={e => setPassword(e.target.value)} value={password} />
            </Form.Group>
            <Form.Label>Role</Form.Label>
            <Form.Select aria-label="Role Selection" defaultValue="none" placeholder='Choose Role' onChange={e => setRole(e.target.value)}>
              <option disabled={true} value="none">Choose Role ...</option>
              <option value="student">Student</option>
              <option value="professor">Professor</option>
              <option value="admin">Admin</option>
            </Form.Select>
            {errbar !== '' && <p>{errbar}</p>}
            <Form.Group className="mb-3" controlId="formBasicText">
              <Form.Label>New Password</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter new password"
                onChange={e => {
                  setErrbar('')
                  setPass(e.target.value)
                }}
                value={pass} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicText">
              <Form.Control
                type="text"
                placeholder="Reconfirm new password"
                onChange={e => {
                  setErrbar('')
                  setPass2(e.target.value)
                }}
                value={pass2} />
            </Form.Group>
            <Button variant="primary" type="submit" onClick={login}>
              Login
            </Button>
          </Form>
        </div>
      </div>
    </>
  )
}
