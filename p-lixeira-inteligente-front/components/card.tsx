export default function Card({...props}){

  let bgColor = '';
  let content = props.item.content;

  switch (props.item.content){
    case '0,0,0':
      bgColor = 'rgb(0, 0, 0)';
      content = 'Preto';
      break;
    case '0,255,0':
      bgColor = 'rgb(0, 255, 0)';
      content = 'Verde';
      break;
    case '255,255,0':
      bgColor = 'rgb(255, 255, 0)';
      content = 'Amarelo';
      break;  
    case '255,120,0':
      bgColor = 'rgb(255, 120, 0)';
      content = 'Laranja';
      break; 
    case '255,0,0':
      bgColor = 'rgb(255, 0, 0)';
      content = 'Vermelho';
      break;
    default:
      bgColor = 'rgb(224 231 255)';
      content = props.item.content;
  }

  return(
    <section className="w-48 h-48 flex items-center justify-start flex-col">
      <section className={`p-4 m-4 shadow-xl rounded-md w-48 h-32 flex items-center justify-center opacity-80`} style={{background: bgColor}}>        
        <span className={bgColor == 'rgb(255, 120, 0)' || bgColor == 'rgb(255, 0, 0) text-center' ? 'text-white text-center' : 'text-center'}>{content}</span>
      </section>
      <span className="text-sm text-center w-full text-gray-600">{props.item.description}</span>
    </section>
  )
}