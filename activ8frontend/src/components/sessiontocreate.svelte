<script>
    
  </script>
  
  <style>
    .session-container {
      position: absolute;
      top: 10%;
      left: 20%;
      transform: translate(-50%, -50%);
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20px;
      z-index: 1;

    }
  
    .back-button {
      display: flex;
      align-items: center;
      padding: 0;
      font-weight: light;
      font-size: 15px;
      text-align: center;
      color: #000;
      cursor: pointer;
      z-index: 1;
    }
  
    .back-arrow {
    margin-right: 5px;
    width: 0;
    height: 0;
    border-top: 5px solid transparent;
    border-bottom: 5px solid transparent;
    border-right: 5px solid #000;
  }

  </style>
  
  
  <div class="session-container">
    <div class="back-button">
      <div class="back-arrow"></div>
      Back
    </div>
    <!-- Add your session content here -->
  </div>
  