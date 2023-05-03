import React, { useState } from 'react'
import { BasNav } from './components/BasNav'
import styles from './Home.module.css'
import { Button, Form } from 'react-bootstrap'
import { userstore } from '../../redux/store'
import { useNavigate } from 'react-router-dom'

export const Home = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [role, setRole] = useState('student')
  const navi = useNavigate()
  const login = e => {
    e.preventDefault()
    fetch( import.meta.env.VITE_BACK  + role + 's/login', {
      method:"POST",
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      body:JSON.stringify({
        username: username,
        password: password
      })
    })
    .then(resp => resp.json())
    .then(data => {
      userstore.dispatch({ type: 'userinfo/login', payload: {...data, role: role, userid:data.userId}})
      switch (role) {
        case 'student':
          navi('/student/course')
          break
        case 'professor':
          navi('/professor/grade')
          break
        case 'admin':
          navi('/admin/report')
          break
        default:
          break
      }
    })
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
            <Form.Select aria-label="Role Selection" defaultValue={"none"} placeholder='Choose Role' onChange={e => setRole(e.target.value)}>
              <option disabled={true} value="none">Choose Role ...</option>
              <option value="student">Student</option>
              <option value="professor">Professor</option>
              <option value="admin">Admin</option>
            </Form.Select>
            <Button variant="primary" type="submit" onClick={login}>
              Login
            </Button>
          </Form>
        </div>
      </div>
    </>
  )
}
