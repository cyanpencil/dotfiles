from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/', defaults={'path': ''}, methods=['GET', 'POST', 'PUT', 'DELETE'])
@app.route('/<path:path>', methods=['GET', 'POST', 'PUT', 'DELETE'])
def echo(path):
    return jsonify(headers=str(request.headers),
                   args=request.args,
                   form=request.form,
                   method=request.method,
                   path=request.path,
                   url=request.url)

if __name__ == '__main__':
    app.run(debug=True)
