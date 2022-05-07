const LoadingComponent = () => {
    return (
        <div className="mt-5">
            <div className = "d-flex justify-content-center align-items-center">
                <div class="spinner-grow text-info" role="status" style={{width: "2rem", height: "2rem"}}>
                  <span class="visually-hidden color color-primary">Loading...</span>
                </div>
                <div class="spinner-grow text-warning mx-2" role="status" style={{width: "4rem", height: "4rem"}}>
                  <span class="visually-hidden">Loading...</span>
                </div>
                <div class="spinner-grow text-success" role="status" style={{width: "2rem", height: "2rem"}}>
                  <span class="visually-hidden">Loading...</span>
                </div>
            </div>
        </div>
    );
}

export default LoadingComponent
