import React, { useState } from 'react'
import styles from './Student.module.css'
import { BasNav } from './components/BasNav'
import { Button, Form } from 'react-bootstrap'

export const Student = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const login = e => {
        e.preventDefault()
    }
    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <h1>Student Login</h1>
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
                        <Button variant="primary" type="submit" onClick={login}>
                            Login
                        </Button>
                    </Form>
                </div>
            </div>
        </>
    )
}
