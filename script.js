const form = document.getElementById('bookingForm');
const msg = document.getElementById('clientMsg');

form.addEventListener('submit', function(e) {
  const checkin = new Date(this.checkin.value);
  const checkout = new Date(this.checkout.value);

  if (checkout <= checkin) {
    e.preventDefault();
    msg.textContent = '❗ Checkout must be after check-in.';
    msg.style.color = 'red';
  } else {
    msg.textContent = 'Processing...';
    msg.style.color = 'green';
  }
});
function updateRooms(data) {
  const container = document.getElementById("rooms");
  container.innerHTML = "";
  for (const [room, avail] of Object.entries(data)) {
    const div = document.createElement("div");
    div.textContent = `Room ${room}\n${avail ? "Available" : "Occupied"}`;
    div.className = "room " + (avail ? "available" : "occupied");
    container.appendChild(div);
  }
}

function fetchStatus() {
  fetch("roomStatus")
    .then(res => res.json())
    .then(data => updateRooms(data))
    .catch(console.error);
}

// initial fetch, then every 5 seconds
fetchStatus();
setInterval(fetchStatus, 5000);
