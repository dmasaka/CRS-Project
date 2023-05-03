import React, { useEffect, useState } from 'react'
import styles from '../Admin.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

export const Approval = () => {
  const [unapprove, setUnapprove] = useState([])
  const [refresh, setRefresh] = useState(true)
  const navi = useNavigate()
  const approveone = e => {
    console.log(e.target.id)
    fetch( import.meta.env.VITE_BACK + 'students/approve/' + e.target.id, { method:"POST" })
    navi()
    setTimeout(() => {
      setRefresh(!refresh)
    }, 2000);
  }
  const approveall = e => {
    fetch( import.meta.env.VITE_BACK + 'students/approveall', { method:"POST" })
    setTimeout(() => {
      setRefresh(!refresh)
    }, 2000);
  }

  useEffect(() => {
    console.log('useeffect')
    fetch( import.meta.env.VITE_BACK + 'students/unapproved')
      .then(resp => resp.json())
      .then(data => setUnapprove(data))
  }, [setUnapprove, refresh])
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
          <div>
            <p>Approve Student Registration</p>
            <table>
              <tbody>
                <tr>
                  <th>Name</th>
                  <th>Username</th>
                  <th></th>
                </tr>
                {
                  unapprove.map(item => (
                    <tr key={item.userId}>
                      <td>{item.name}</td>
                      <td>{item.username}</td>
                      <td>
                        <Button variant='success' type='submit' onClick={approveone} id={item.userId}>Approve</Button>
                      </td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
            <Button variant="danger" type="submit" onClick={approveall}>
              Approve All
            </Button>
          </div>
        </div>
      </div>
    </>
  )
}
