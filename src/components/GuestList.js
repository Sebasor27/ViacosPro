import React from "react"

function GuestList({ guests, onClose }) {
  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Lista de Invitados</h2>
        <ul>
          {guests.map((guest, index) => (
            <li key={index}>
              <strong>{guest.nombre}</strong> - {guest.cargo}
            </li>
          ))}
        </ul>
        <button onClick={onClose}>Cerrar</button>
      </div>
    </div>
  )
}

export default GuestList

