<template>
    <footer class="footer">
        <p class="labelLinks">Get connected with us on social networks</p>
        <div class="line"></div>
        <p><strong>DAC project &copy; 2021</strong></p>
        <div class="line"></div>
        <p class="labelDeveloped"><strong>Developed by:</strong><br><br>João Arthur<br>Lucas Nascimento<br>Thauan Amorim</p>

    </footer>
</template>

<script>
export default {
    
}
</script>

<style scoped>

.footer {
    height: 160px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    border-top: 50px solid #FCB13A;
    background: #955ABE;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.line {
    height: 90px;
    width: 1px;
    background: black;
}

p {
    padding: 0px 100px;
    font-size: 18px;
}

.labelLinks {
    margin-bottom: 75px;
}

</style>