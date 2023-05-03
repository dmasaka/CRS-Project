import React, { useState } from 'react'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import styles from '../Home.module.css'

export const Register = () => {
    //name, username, password, address, semester, branch
    const [name, setName] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [pass2, setPass2] = useState('')
    const [address, setAddress] = useState('')
    const [semester, setSemester] = useState(0)
    const [branch, setBranch] = useState('')
    const [errbar, setErrbar] = useState('')
    let sub = e => {
        e.preventDefault()
        if (password === pass2){
        fetch( import.meta.env.VITE_BACK + 'students/add', {
            method: "POST",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body:JSON.stringify({
                "name":name,
                "username":username,
                "password":password,
                "address":address,
                "semester":semester,
                "branch":branch
            })
        })
        .then(resp => resp.json())
        .then(data => console.log(data))
    } else {
        setErrbar("Passwords do not match")
    }
        // redirect('/student')
    }
    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                {/* use the semester registration */}
                <div className={styles.SerCont}>
                    <div>
                    { errbar !== '' && <p>{errbar}</p>}
                        <Form className={styles.FormSin}>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Name</Form.Label>
                                <Form.Control type="text" placeholder="Enter name" onChange={e => setName(e.target.value)} value={name} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} value={username} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="text" placeholder="Enter password" onChange={e => setPassword(e.target.value)} value={password} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Control type="text" placeholder="Reconfirm password" onChange={e => setPass2(e.target.value)} value={pass2} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="text" placeholder="Enter address" onChange={e => setAddress(e.target.value)} value={address} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Branch</Form.Label>
                                <Form.Control type="text" placeholder="Enter branch" onChange={e => setBranch(e.target.value)} value={branch} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicNumber">
                                <Form.Label>Semester</Form.Label>
                                <Form.Control type="number" placeholder="Enter semester" onChange={e => setSemester(e.target.value)} value={semester} />
                            </Form.Group>
                            <Button variant="primary" type="submit" onClick={sub}>
                                Register
                            </Button>
                        </Form>
                    </div>
                </div>
            </div>
        </>
    )
}
