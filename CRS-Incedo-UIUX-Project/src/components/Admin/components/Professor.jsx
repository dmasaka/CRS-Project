import React, { useEffect, useState } from 'react'
import styles from '../Admin.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'

export const Professor = () => {
  const [coursecode, setcoursecode] = useState('')
  const [profid, setProfid] = useState('')
  const [profs, setProfs] = useState([])
  const [courses, setCourses] = useState([])
  const [name, setName] = useState('')
  const [username, setUsername] = useState('')
  const [pass, setPass] = useState('')
  const [pass2, setPass2] = useState('')
  const [addr, setAddr] = useState('')
  const [depart, setDepart] = useState('')
  const [desig, setDesig] = useState('')
  const [doj, setDoj] = useState('')
  //name, username, password, address, department, designation, doj
  useEffect(() => {
    fetch( import.meta.env.VITE_BACK + 'professors/all')
      .then(resp => resp.json())
      .then(data => setProfs(data))
    fetch( import.meta.env.VITE_BACK + 'course/all')
      .then(resp => resp.json())
      .then(data => setCourses(data))
  }, [setProfs, setCourses])

  const register = e => {
    e.preventDefault()
    fetch( import.meta.env.VITE_BACK + 'professors/add', {
      method:"POST",
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      body: JSON.stringify({
        "name":name,
        "username":username,
        "password":pass,
        "address":addr,
        "department":depart,
        "designation":desig,
        "doj":doj
      })
    })
    .then(resp => resp.json())
    .then(data => console.log(data))
  }
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
          <div>
            <p>Register Professor</p>
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
                <Form.Control type="text" placeholder="Enter password" onChange={e => setPass(e.target.value)} value={pass} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Control type="text" placeholder="Reconfirm password" onChange={e => setPass2(e.target.value)} value={pass2} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Address</Form.Label>
                <Form.Control type="text" placeholder="Enter address" onChange={e => setAddr(e.target.value)} value={addr} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Department</Form.Label>
                <Form.Control type="text" placeholder="Enter department" onChange={e => setDepart(e.target.value)} value={depart} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Designation</Form.Label>
                <Form.Control type="text" placeholder="Enter designation" onChange={e => setDesig(e.target.value)} value={desig} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicDate">
                <Form.Label>DOJ</Form.Label>
                <Form.Control type="date" placeholder="Enter date of joining" onChange={e => setDoj(e.target.value)} value={doj} />
              </Form.Group>
              <Button variant="primary" type="submit" onClick={register}>
                Register
              </Button>
            </Form>
          </div>
          <div>
            <p>Add Professor To Course</p>
            <Form className={styles.FormSin}>
              <Form.Label>Course</Form.Label>
              <Form.Select aria-label="Professor Selection" defaultValue={0}  placeholder='Choose professor' onChange={e => setcoursecode(e.target.value)}>
                <option disabled={true} value={1}>Choose Course ...</option>
                {courses.map(item => (
                  <option value={item.courseCode} key={item.courseCode}>{item.courseCode}</option>
                ))}
              </Form.Select>
              <Form.Label>Professor Id</Form.Label>
              <Form.Select aria-label="Professor Selection" defaultValue={0}  placeholder='Choose professor' onChange={e => setProfid(e.target.value)}>
                <option disabled={true} value={1}>Choose Professor ...</option>
                {profs.map(item => (
                  <option value={item.userId} key={item.userId}>{item.username}</option>
                ))}
              </Form.Select>
              <Button variant="primary" type="submit">
                Add
              </Button>
            </Form>
          </div>
        </div>
      </div>
    </>
  )
}
