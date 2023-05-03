import React, { useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { userstore } from '../../../redux/store'

export const Register = () => {
    //name, username, password, address, semester, branch
    const [semester, setSemester] = useState(0)
    const nstore = userstore.getState()
    let sub = e => {
        e.preventDefault()
        fetch(import.meta.env.VITE_BACK + '/students/semester', {
            method:'POST',
            headers: new Headers({
                'Content-Type':'application/json'
            }),
            body: JSON.stringify({
                studentid: nstore.userid,
                semester: semester
            })
        })
        .then(resp => resp.json())
        .then(data => console.log(data))
    }
    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                {/* use the semester registration */}
                <div className={styles.SerCont}>
                    <div>
                        <p>Register for Semester</p>
                        <Form className={styles.FormSin}>
                            <Form.Label>Semester</Form.Label>
                            <Form.Select aria-label="Semester Selection" defaultValue={0} placeholder='Choose semester' onChange={e => setSemester(e.target.value)}>
                                    <option disabled={true} value={0} key={"soup"}>Choose Semester ...</option>
                                    <option value={1} key={1}>Fall</option>
                                    <option value={2} key={2}>Spring</option>
                                    <option value={3} key={3}>Summer</option>
                                </Form.Select>
                            <Button variant="primary" type="submit" onClick={sub} className={styles.ButTop}>
                                Register
                            </Button>
                        </Form>
                    </div>
                </div>
            </div>
        </>
    )
}
