import { useState, useEffect } from 'react';
import { Link, useLoaderData, useParams, NavLink, useNavigate } from 'react-router-dom'
import { TextField, Checkbox, FormControlLabel, Button } from '@mui/material';
import EditCard from '../../components/EditCard';
import BasicModal from '../../components/BasicModal';
import { useTranslation } from "react-i18next";

export default function GemDetails() {
  const { id } = useParams();
  const gem = useLoaderData();
  const navigate = useNavigate();
  const { t } = useTranslation();

  const [editGem, setEditGem] = useState(true);
  const [title, setTitle] = useState(gem.title);
  const [origin, setOrigin] = useState(gem.origin);
  const [reserved, setReserved] = useState(gem.reserved);
  const originEditButton = editGem ? t('edit') : t('back');

  const gemPom = { title, origin, reserved };

  // MODAL VARIABLES
  const [open, setOpen] = useState(false);
  const [modalText, setModalText] = useState("");

  const deleteGem = id => {
    fetch(`http://localhost:8080/api/v1/gems/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        setModalText(t('recordDeleted'));
        setOpen(true);
      })
      .then(() => new Promise((resolve) => setTimeout(resolve, 1500)))
      .then(() => navigate('../../gems'));
  }

  const changeGem = id => {
    fetch(`http://localhost:8080/api/v1/gems/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(gemPom)
    })
      .then(() => {
        setModalText(t('recordChanged'));
        setOpen(true);
      })
      .then(() => new Promise((resolve) => setTimeout(resolve, 1500)))
      .then(() => navigate('../../gems'));
  }

  const handleEditClick = () => {
    setTitle(gem.title);
    setOrigin(gem.origin);
    setReserved(gem.reserved);
    setEditGem((prev) => !prev);
  }

  return (
    <div>
      <div>
        {editGem && <button className="gemBtn" onClick={() => navigate('../../gems')}>{t('back')}</button>}
        <button className="gemBtn" onClick={handleEditClick}>{originEditButton}</button>
        {editGem && <button className="gemBtn" onClick={() => deleteGem(id)}>{t('delete')}</button>}
      </div>
      {editGem ?
        <div className="gem-details">
          <EditCard gem={gem} key={gem.id} width="400px" height="400px" />
        </div>
        : <div className="createDiv">
          <div style={{ marginBottom: "20px", display: "flex", justifyContent: "center" }}>
            <EditCard gem={gemPom} key={gem.id} width="400px" height="400px" />
          </div>
          <TextField
            value={title}
            id="titleCreate"
            label={t('title')}
            variant="outlined"
            fullWidth
            style={{ margin: "5px 0px" }}
            onChange={(e) => {
              setTitle(e.target.value);
            }} />
          <TextField
            value={origin}
            id="titleOrigin"
            label={t('origin')}
            variant="outlined"
            fullWidth
            style={{ margin: "5px 0px" }}
            onChange={(e) => setOrigin(e.target.value)} />
          <FormControlLabel control={<Checkbox checked={reserved} onChange={(e) => setReserved(e.target.checked)} />} label={t('reserved')} />
          <Button
            variant="contained"
            style={{ display: "block", margin: "5px auto" }}
            onClick={() => changeGem(id)}
          >{t('change')}</Button>
        </div>
      }
      <BasicModal text={modalText} open={open}
      />
    </div >
  )
}

// data loader
export const gemDetailsLoader = async ({ params }) => {
  const { id } = params

  const res = await fetch('http://localhost:8080/api/v1/gems/' + id)

  return res.json()
}