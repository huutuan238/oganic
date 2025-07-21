<template>
    <h3 class="text-center" style="color: #7fad39;">
        Login
    </h3>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form id="registrationForm" action="">
                            <div class="form-group">
                                <label for="email">
                                    Email
                                </label>
                                <input type="email" class="form-control" id="email" placeholder="Email" required />
                            </div>
                            <div class="form-group">
                                <label for="password">
                                    Password
                                </label>
                                <input type="password" class="form-control" id="password" placeholder="Password"
                                    required />
                            </div>
                            <button class="site-btn">
                                Login
                            </button>
                        </form>
                        <p class="mt-3">
                            Not registered?
                            <a th:href="@{'./register'}" style="color: #7fad39;">Create an account</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>